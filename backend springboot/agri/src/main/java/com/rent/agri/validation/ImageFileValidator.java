package com.rent.agri.validation;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.validation.annotation.ValidImage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageFileValidator implements ConstraintValidator<ValidImage, MultipartFile> {

    private long maxSize;
    private String[] contentTypes;

    @Override
    public void initialize(ValidImage constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
        this.contentTypes = constraintAnnotation.contentTypes();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        if (file.getSize() > maxSize) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "File size exceeds " + (maxSize / 1024) + " KB"
            ).addConstraintViolation();
            return false;
        }

        if (!Arrays.asList(contentTypes).contains(file.getContentType())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Only JPEG or PNG images are allowed"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}
