package com.rent.agri.validation;

import com.rent.agri.model.dto.RentalRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class RentalRequestValidator implements ConstraintValidator<ValidRentalRequest, RentalRequest> {

    @Override
    public boolean isValid(RentalRequest request, ConstraintValidatorContext context) {
        boolean valid = true;

        context.disableDefaultConstraintViolation();

        if (request.getEqupId() == null || request.getEqupId() <= 0) {
            context.buildConstraintViolationWithTemplate("Equipment ID must be positive and required")
                    .addPropertyNode("equpId").addConstraintViolation();
            valid = false;
        }

        LocalDateTime start = request.getStartTime();
        LocalDateTime end = request.getEndTime();

        if (start == null) {
            context.buildConstraintViolationWithTemplate("Start time is required")
                    .addPropertyNode("startTime").addConstraintViolation();
            valid = false;
        } else if (!start.isAfter(LocalDateTime.now())) {
            context.buildConstraintViolationWithTemplate("Start time must be in the future")
                    .addPropertyNode("startTime").addConstraintViolation();
            valid = false;
        }

        if (end == null) {
            context.buildConstraintViolationWithTemplate("End time is required")
                    .addPropertyNode("endTime").addConstraintViolation();
            valid = false;
        } else if (start != null && !end.isAfter(start)) {
            context.buildConstraintViolationWithTemplate("End time must be after start time")
                    .addPropertyNode("endTime").addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
