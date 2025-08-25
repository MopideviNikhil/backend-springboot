package com.rent.agri.model.dto;

import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.model.enums.Gender;
import com.rent.agri.model.enums.Role;
import com.rent.agri.validation.annotation.ValidGender;
import com.rent.agri.validation.annotation.ValidImage;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccount {

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be less than or equal to 120")
    private int age;

   @ValidGender
    private Gender gender;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(
        regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
        message = "Phone number must be valid and contain 10 digits"
    )
    private String phoneNumber;

    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @NotBlank(message = "Password is required")
    @Pattern(
        regexp = "^(?=(?:.*\\d){4,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
        message = "Password must be at least 8 characters long, contain at least 1 uppercase letter, 1 lowercase letter, 1 special character, and at least 4 digits"
    )
    private String password;

    private Role role;

    @NotNull(message = "Image is required")
    @ValidImage(maxSize = 2_000_000, contentTypes = {"image/jpeg", "image/png"})
    private MultipartFile imageDto;
}

