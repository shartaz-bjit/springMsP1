package com.feeham.exception.services;

import com.feeham.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<?> exHandler1() {
        return new ResponseEntity<>(new CustomArithmaticException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<?> exHandler2(){
        return new ResponseEntity<>(new CustomNumberFormationException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<?> exHandler3() {
        return new ResponseEntity<>(new CustomNullException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicateEntityException.class})
    public ResponseEntity<?> exHandler4() {
        return new ResponseEntity<>(new DuplicateEntityException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class })
    public ResponseEntity<?> exHandler5() {
        return new ResponseEntity<>(new EntityNotFoundException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> exHandler6() {
        return new ResponseEntity<>("An error has occurred!", HttpStatus.BAD_REQUEST);
    }
}
