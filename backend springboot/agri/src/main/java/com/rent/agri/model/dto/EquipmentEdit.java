package com.rent.agri.model.dto;

import com.rent.agri.validation.annotation.AtLeastOneField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AtLeastOneField
public class EquipmentEdit {
    private String title;
    private String category;
    private String description;
    private double price;
    private String location;
 }
