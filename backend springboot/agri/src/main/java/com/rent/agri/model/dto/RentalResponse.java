package com.rent.agri.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalResponse {
	private Long RenId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double price;
}
