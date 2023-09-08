package com.spring.securityPractice.model;

import lombok.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class UserLoginCredResponse {
    private String userId;
    private String username;
    private String bearerToken;
}
