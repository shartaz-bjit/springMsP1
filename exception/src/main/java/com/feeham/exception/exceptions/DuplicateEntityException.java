package com.feeham.exception.exceptions;

public class DuplicateEntityException extends Exception{
    private static final String MESSAGE = "Duplicate entity can't be taken!";
    public DuplicateEntityException() {
        super(MESSAGE);
    }
}
