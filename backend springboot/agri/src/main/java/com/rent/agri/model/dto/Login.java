package com.rent.agri.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=(?:.*\\d){4,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be at least 8 characters long, contain at least 1 uppercase letter, 1 lowercase letter, 1 special character, and at least 4 digits"
        )
    private String password;
}
