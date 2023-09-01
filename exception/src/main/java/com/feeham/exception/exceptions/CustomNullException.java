package com.feeham.exception.exceptions;

public class CustomNullException extends Exception {
    private static final String MESSAGE = "Null value was provided!";
    public CustomNullException() {
        super(MESSAGE);
    }
}

