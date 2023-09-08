package com.spring.securityPractice.service;


import com.spring.securityPractice.model.UserCreateDTO;
import com.spring.securityPractice.model.UserLoginDTO;
import com.spring.securityPractice.model.UserReadDto;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserReadDto createUser(UserCreateDTO user) throws Exception;
    UserReadDto getUser(String email);
    UserReadDto getUserByUserId(String id) throws Exception;

    UserReadDto login(UserLoginDTO userDto);
//    Authentication authenticate(UserLoginDTO userLoginDTO);
}