package com.spring.securityPractice.model;

import com.spring.securityPractice.entity.RoleEntity;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReadDto {
    private long id;
    private String userId;
    private String email;
    private String token;
    private List<String> roles;
}