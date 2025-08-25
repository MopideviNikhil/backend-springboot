package com.rent.agri.validation;

import com.rent.agri.model.dto.RentalRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RentalPeriodValidator implements ConstraintValidator<ValidRentalPeriod, RentalRequest> {

    @Override
    public boolean isValid(RentalRequest request, ConstraintValidatorContext context) {
        if (request.getStartTime() == null || request.getEndTime() == null) {
            return true; 
        }
        return request.getEndTime().isAfter(request.getStartTime());
    }
}
