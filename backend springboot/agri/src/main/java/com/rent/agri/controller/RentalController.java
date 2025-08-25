package com.rent.agri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rent.agri.model.dto.RentalRequest;
import com.rent.agri.model.dto.RentalResponse;
import com.rent.agri.service.RentalService;
import com.rent.agri.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rental")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET,
		RequestMethod.PUT })
public class RentalController {
	
	@Autowired
	private RentalService rentalService;
	
	  @PostMapping
	    public ResponseEntity<ApiResponse<RentalResponse>> createRental(@Valid @RequestBody RentalRequest request) {
	        RentalResponse saved = rentalService.createRental(request);
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(ApiResponse.<RentalResponse>builder()
	                        .message("Rental created successfully")
	                        .success(true)
	                        .status(HttpStatus.CREATED.value())
	                        .data(saved)
	                        .build());
	    }
	
}