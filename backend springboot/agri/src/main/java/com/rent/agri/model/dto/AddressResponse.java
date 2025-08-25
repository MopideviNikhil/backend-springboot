package com.rent.agri.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
    private Long addressId;
    private String houseNumber;
    private String street;
    private String landmark;
    private String village;
    private String pincode;
    private String mandal;
    private String district;
    private String state;
}
