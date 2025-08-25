package com.rent.agri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rent.agri.model.dto.EquipmentDto;
import com.rent.agri.model.dto.EquipmentResponse;
import com.rent.agri.model.dto.EquipmentResponseL;
import com.rent.agri.model.entity.Equipment;
import com.rent.agri.service.EquipmentService;
import com.rent.agri.util.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET,
		RequestMethod.PUT })
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;

	@PostMapping
	public ResponseEntity<ApiResponse<EquipmentResponse>> createEquipment(@Valid @ModelAttribute EquipmentDto dto) {
		EquipmentResponse saved = equipmentService.saveEquipment(dto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.<EquipmentResponse>builder().message("Equipment created successfully").success(true)
						.status(HttpStatus.CREATED.value()).data(saved).build());
	}

	@PostMapping("/{id}/upload-image")
	public ResponseEntity<ApiResponse<String>> uploadImage(@PathVariable Long id, @RequestParam MultipartFile file) {
		equipmentService.uploadEquipmentImage(id, file);
		return ResponseEntity.ok(ApiResponse.<String>builder().message("Image uploaded successfully").success(true)
				.status(HttpStatus.OK.value()).data("Image uploaded for equipment ID: " + id).build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EquipmentResponse>> updateEquipment(@PathVariable 
            @Min(value = 1, message = "Equipment ID must be greater than 0") Long id,
			@RequestBody EquipmentDto dto) {
		EquipmentResponse updated = equipmentService.updateEquipment(id, dto);
		return ResponseEntity.ok(ApiResponse.<EquipmentResponse>builder().message("Equipment updated successfully")
				.success(true).status(HttpStatus.OK.value()).data(updated).build());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<Equipment>>> getAllEquipment() {
		List<Equipment> list = equipmentService.getAllEquipment();
		return ResponseEntity.ok(ApiResponse.<List<Equipment>>builder().message("All equipment fetched successfully")
				.success(true).status(HttpStatus.OK.value()).data(list).build());
	}

	// Get equipment by user email
	@GetMapping("/email/{email}")
	public ResponseEntity<ApiResponse<List<EquipmentResponseL>>> getByEmail(
			@PathVariable @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email) {
		List<EquipmentResponseL> list = equipmentService.getByEmail(email);
		return ResponseEntity
				.ok(ApiResponse.<List<EquipmentResponseL>>builder().message("Equipment fetched for email: " + email)
						.success(true).status(HttpStatus.OK.value()).data(list).build());
	}



	@GetMapping("/location/{location}")
	public ResponseEntity<ApiResponse<List<EquipmentResponseL>>> getByLocation(
			@PathVariable @NotBlank(message = "Location must not be blank") @Size(min = 2, max = 50, message = "Location length must be between 2 and 50 characters") @Pattern(regexp = "^[A-Za-z ]+$", message = "Location must contain only letters and spaces") String location) {
		List<EquipmentResponseL> list = equipmentService.getByLocation(location);
		return ResponseEntity
				.ok(ApiResponse.<List<EquipmentResponseL>>builder().message("Equipment fetched for email: " + location)
						.success(true).status(HttpStatus.OK.value()).data(list).build());
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<ApiResponse<List<EquipmentResponseL>>> getByCategory(
			@PathVariable @NotBlank(message = "Category must not be blank") @Size(min = 3, max = 30, message = "Category length must be between 3 and 30 characters") @Pattern(regexp = "^[A-Za-z ]+$", message = "Category must contain only letters and spaces") String category) {
		List<EquipmentResponseL> list = equipmentService.getByCategory(category);
		return ResponseEntity.ok(
				ApiResponse.<List<EquipmentResponseL>>builder().message("Equipment fetched for category: " + category)
						.success(true).status(HttpStatus.OK.value()).data(list).build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteEquipmentById(
			@PathVariable @Min(value = 1, message = "Equipment ID must be greater than 0") Long id) {
		equipmentService.deleteEquipment(id);
		return ResponseEntity.ok(ApiResponse.<Void>builder().message("Equipment deleted successfully").success(true)
				.status(HttpStatus.OK.value()).build());
	}

}
