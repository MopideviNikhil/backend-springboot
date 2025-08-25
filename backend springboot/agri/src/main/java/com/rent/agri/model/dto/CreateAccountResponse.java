package com.rent.agri.model.dto;

import com.rent.agri.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountResponse {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String email;
    private String phoneNumber;
}
