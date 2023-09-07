package com.spring.securityPractice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.securityPractice.SpringApplicationContext;
import com.spring.securityPractice.constants.AppConstants;
import com.spring.securityPractice.model.UserCreateDTO;
import com.spring.securityPractice.model.UserLoginCredResponse;
import com.spring.securityPractice.model.UserLoginDTO;
import com.spring.securityPractice.model.UserReadDto;
import com.spring.securityPractice.service.UserService;
import com.spring.securityPractice.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final Map<String, Integer> attemptCount = new HashMap<String, Integer>();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginDTO credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), UserLoginDTO.class);
        } catch (IOException e) {
            writeResponse(response, "Exception while reading credentials");
        }
        attemptCount.put(credentials.getEmail(), attemptCount.getOrDefault(credentials.getEmail(), 0) + 1);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(),credentials.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String user = ((User) authResult.getPrincipal()).getUsername();
        String accessToken = JWTUtils.generateToken(user);
        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
        UserReadDto userDto = userService.getUser(user);
        if(attemptCount.get(userDto.getEmail()) > AppConstants.MAX_LOGIN_ATTEMPTS_LIMIT){
            restrictedResponse(response);
            return;
        }
        else attemptCount.put(userDto.getEmail(), 0);
        UserLoginCredResponse responseBody = new UserLoginCredResponse(userDto.getUserId(), userDto.getEmail(), AppConstants.TOKEN_PREFIX + accessToken);
        writeResponse(response, responseBody);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Error", "Authentication failed!");
        if(failed instanceof UsernameNotFoundException){
            errorResponse.put("Cause: ", "User not found!");
        }
        else if(failed instanceof BadCredentialsException){
            errorResponse.put("Cause: ", "Incorrect password!");
        }
        errorResponse.put("Limit", "Max attempt: "+AppConstants.MAX_LOGIN_ATTEMPTS_LIMIT);
        writeResponse(response, errorResponse);
    }

    private void restrictedResponse(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Restricted", "Your account has been locked for "+AppConstants.MAX_LOGIN_ATTEMPTS_LIMIT +" failed attempts.");
        writeResponse(response, errorResponse);
    }

    private void writeResponse(HttpServletResponse response, Object object){
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        try{
            new ObjectMapper().writeValue(response.getWriter(), object);
        }
        catch (IOException ioe){
            log.error("Failed to write in response.", ioe);
        }
    }
}