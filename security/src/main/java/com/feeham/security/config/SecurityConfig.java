package com.feeham.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->{
                    auth
                            .requestMatchers(HttpMethod.GET, "/profile").hasRole("user")
                            .requestMatchers(HttpMethod.GET, "/logout").hasRole("user")
                            .requestMatchers(HttpMethod.GET, "/adminDashboard").hasRole("admin")
                            .requestMatchers(HttpMethod.GET, "/edit_profile").hasRole("user")
                            .anyRequest().permitAll();
                });
        return http.build();
    }
}
