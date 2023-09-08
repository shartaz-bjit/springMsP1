package com.spring.securityPractice.controller;

import com.spring.securityPractice.constants.AppConstants;
import com.spring.securityPractice.model.UserCreateDTO;
import com.spring.securityPractice.model.UserLoginCredResponse;
import com.spring.securityPractice.model.UserLoginDTO;
import com.spring.securityPractice.model.UserReadDto;
import com.spring.securityPractice.service.UserService;
import com.spring.securityPractice.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
public class SigningController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping(AppConstants.SIGN_UP)
    public ResponseEntity<UserReadDto> register (@RequestBody UserCreateDTO userDto) throws Exception {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @PostMapping(AppConstants.SIGN_IN)
    public ResponseEntity<?> login (@RequestBody UserLoginDTO userDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getEmail(),
                            userDto.getPassword()
                    )
            );

            var user = userService.getUser(userDto.getEmail());
            var jwtToken = JWTUtils.generateToken(user.getEmail(), user.getRoles());
            return new ResponseEntity<>(UserLoginCredResponse.builder()
                    .userId(user.getUserId())
                    .username(user.getEmail())
                    .bearerToken(jwtToken)
                    .build(), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
        }  catch (Exception e) {
            return new ResponseEntity<>("Failed to authenticate", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
