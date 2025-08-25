package com.rent.agri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rent.agri.model.dto.ReviewRequest;
import com.rent.agri.model.dto.ReviewResponse;
import com.rent.agri.service.ReviewService;
import com.rent.agri.util.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET,
		RequestMethod.PUT })
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody ReviewRequest request) {
          ReviewResponse saved = reviewService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ReviewResponse>builder()
                        .message("review created successfully")
                        .success(true)
                        .status(HttpStatus.CREATED.value())
                        .data(saved)
                        .build());
    }
  
    
    @GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable 
            @Min(value = 1, message = "Review ID must be greater than 0") Long id) {
		 ReviewResponse review = reviewService.getReviewById(id); 
		return ResponseEntity.ok(ApiResponse.<ReviewResponse>builder().message("review fetched successfully")
				.success(true).status(HttpStatus.OK.value()).data(review).build());
	}

   
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getAllReviews() {
		 List<ReviewResponse> review = reviewService.getAllReviews();
		return ResponseEntity.ok(ApiResponse.<List<ReviewResponse>>builder().message("review fetched successfully")
				.success(true).status(HttpStatus.OK.value()).data(review).build());
	}
    

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReviewById(@PathVariable 
            @Min(value = 1, message = "Review ID must be greater than 0") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .message("Review deleted successfully")
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }
}
