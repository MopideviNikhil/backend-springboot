package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class CartNotFoundException extends AgriRentalException {
    public CartNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

