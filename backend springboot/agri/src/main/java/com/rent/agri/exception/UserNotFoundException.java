package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AgriRentalException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

