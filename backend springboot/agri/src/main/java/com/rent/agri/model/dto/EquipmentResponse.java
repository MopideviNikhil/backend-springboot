package com.rent.agri.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentResponse {
	private Long equipId;
    private String title;
    private String category;
    private String description;
    private double price;
    private String location;
  }
