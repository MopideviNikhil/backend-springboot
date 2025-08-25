package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class EquipmentNotFoundException extends AgriRentalException {
    public EquipmentNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

