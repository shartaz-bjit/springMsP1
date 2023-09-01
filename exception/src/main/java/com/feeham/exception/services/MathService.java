package com.feeham.exception.services;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MathService {
    /*
     10, 0 -> Arithmetic by 0
     "Hello", 5 -> Number formation
    */
    public String divide(String num1, String num2)
            throws ArithmeticException, NumberFormatException, NullPointerException{
        int n1 = Integer.parseInt(num1.toLowerCase());
        int n2 = Integer.parseInt(num2.toLowerCase());
        int result = n1 / n2;
        return  "Division result: "+result;
    }
}
