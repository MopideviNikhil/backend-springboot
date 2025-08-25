package com.rent.agri.validation;

import java.util.Arrays;

import com.rent.agri.model.enums.Role;
import com.rent.agri.validation.annotation.ValidRole;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, Role> {

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; 
        }
        return Arrays.asList(Role.values()).contains(value);
    }
}
