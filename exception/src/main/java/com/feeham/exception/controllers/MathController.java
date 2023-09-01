package com.feeham.exception.controllers;

import com.feeham.exception.services.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    @Autowired
    private MathService mathService;

    @GetMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam String num1, String num2)
            throws ArithmeticException, NumberFormatException, NullPointerException{
        if(num1 == null || num1.isEmpty()) num1 = null;
        return new ResponseEntity<>(mathService.divide(num1, num2), HttpStatus.OK);
    }
}
