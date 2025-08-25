package com.rent.agri.validation;

import java.util.Arrays;

import com.rent.agri.model.enums.Gender;
import com.rent.agri.validation.annotation.ValidGender;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderEnumValidator implements ConstraintValidator<ValidGender, Gender> {

    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return Arrays.asList(Gender.values()).contains(value);
    }
}
