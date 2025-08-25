package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class BookingAlreadyExistsException extends AgriRentalException {
    public BookingAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}

