package com.spring.securityPractice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
public class UserLoginCredResponse {
    private String userId;
    private String username;
    private String bearerToken;
}
