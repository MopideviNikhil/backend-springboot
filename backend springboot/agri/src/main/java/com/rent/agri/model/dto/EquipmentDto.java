package com.rent.agri.model.dto;

import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.validation.annotation.ValidImage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private Double price;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @NotNull(message = "Image is required")
    @ValidImage(maxSize = 2_000_000, contentTypes = {"image/jpeg", "image/png","image/webp"})
    private MultipartFile imageDto;
}
