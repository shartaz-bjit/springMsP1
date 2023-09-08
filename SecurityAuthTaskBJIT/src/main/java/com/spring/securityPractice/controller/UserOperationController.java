package com.spring.securityPractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserOperationController {
    @GetMapping("/hello")
    public ResponseEntity<?> getHello(){
        return ResponseEntity.ok("Hello user!");
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        return ResponseEntity.ok("Welcome to your profile!");
    }
}
