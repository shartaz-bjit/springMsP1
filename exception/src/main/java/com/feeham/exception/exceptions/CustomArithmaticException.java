package com.feeham.exception.exceptions;

public class CustomArithmaticException extends Exception {
    private static final String MESSAGE = "Math error!";
    public CustomArithmaticException() {
        super(MESSAGE);
    }
}
