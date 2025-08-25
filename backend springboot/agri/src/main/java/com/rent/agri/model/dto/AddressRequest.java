package com.rent.agri.model.dto;

import jakarta.validation.constraints.Email;
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
public class AddressRequest {

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "House number cannot be empty")
    private String houseNumber;

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @NotEmpty(message = "Landmark cannot be empty")
    private String landmark;

    @NotEmpty(message = "Village cannot be empty")
    private String village;

    @NotEmpty(message = "Pincode cannot be empty")
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode must be a valid 6-digit number")
    private String pincode;

    @NotEmpty(message = "Mandal cannot be empty")
    private String mandal;

    @NotEmpty(message = "District cannot be empty")
    private String district;

    @NotEmpty(message = "State cannot be empty")
    private String state;
}
