package com.spring.securityPractice.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReadDto {
    private long id;
    private String userId;
    private String email;
    private String token;
}