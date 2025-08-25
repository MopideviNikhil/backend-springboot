package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends AgriRentalException {
    public EmailNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

