package com.rent.agri.service;

import java.util.List;

import com.rent.agri.model.dto.ReviewRequest;
import com.rent.agri.model.dto.ReviewResponse;

public interface ReviewService {
    ReviewResponse createReview(ReviewRequest review);
    ReviewResponse getReviewById(Long id);
    List<ReviewResponse> getAllReviews();
    void deleteReview(Long id);
}
