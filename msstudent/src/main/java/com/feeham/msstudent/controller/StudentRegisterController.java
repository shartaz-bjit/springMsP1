package com.feeham.msstudent.controller;

import com.feeham.msstudent.service.StudentRegister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRegisterController {
    private final StudentRegister studentRegister;

    public StudentRegisterController(StudentRegister studentRegister) {
        this.studentRegister = studentRegister;
    }

    @GetMapping("/register")
    public String register(){
        return studentRegister.registerStudent("Mr. Pabon mia khalifa");
    }
}
