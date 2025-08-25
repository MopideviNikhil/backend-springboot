package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class PaymentAlreadyProcessedException extends AgriRentalException {
    public PaymentAlreadyProcessedException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
