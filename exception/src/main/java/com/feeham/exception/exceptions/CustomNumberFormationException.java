package com.feeham.exception.exceptions;

public class CustomNumberFormationException extends Exception {
    private static final String MESSAGE = "Failed to generate the number!";
    public CustomNumberFormationException() {
        super(MESSAGE);
    }
}