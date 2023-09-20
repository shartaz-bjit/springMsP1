package com.feeham.msteacher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTeacher {
    @GetMapping("/hello-teacher")
    public String helloTeacher(@RequestParam  String teacherName){
        return "Hello from " + teacherName;
    }
}
