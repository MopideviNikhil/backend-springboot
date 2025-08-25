package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends AgriRentalException {
    public AddressNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

