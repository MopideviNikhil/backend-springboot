package com.rent.agri.validation;

import com.rent.agri.model.dto.EquipmentEdit;
import com.rent.agri.validation.annotation.AtLeastOneField;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneField, EquipmentEdit> {

    @Override
    public boolean isValid(EquipmentEdit dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return false; 
        }
        return (isNotBlank(dto.getTitle()) ||
                isNotBlank(dto.getCategory()) ||
                isNotBlank(dto.getDescription()) ||
                dto.getPrice() > 0 ||
                isNotBlank(dto.getLocation()));
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
