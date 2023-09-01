package com.feeham.exception.exceptions;

public class EntityNotFoundException extends Exception{
    private static final String MESSAGE = "Expected entity doesn't exist!";
    public EntityNotFoundException() {
        super(MESSAGE);
    }
}
