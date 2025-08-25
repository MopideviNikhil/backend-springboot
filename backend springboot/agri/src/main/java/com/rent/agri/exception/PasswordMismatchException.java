package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends AgriRentalException {
    public PasswordMismatchException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}

