package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class BookingNotFoundException extends AgriRentalException {
    public BookingNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

