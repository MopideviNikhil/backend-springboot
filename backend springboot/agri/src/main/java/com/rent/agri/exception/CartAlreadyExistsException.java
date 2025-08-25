package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class CartAlreadyExistsException extends AgriRentalException {
    public CartAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
