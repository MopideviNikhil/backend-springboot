package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends AgriRentalException {
    public ImageNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

