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
public class EditProfile {
	private Long userId;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String phoneNumber;
}
