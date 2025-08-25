package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends AgriRentalException {
    public ReviewNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

