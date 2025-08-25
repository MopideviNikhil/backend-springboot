package com.rent.agri.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {
	private String fileName;
	private String contentType;
	private byte[] imageData;
}
