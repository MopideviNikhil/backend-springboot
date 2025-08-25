package com.rent.agri.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.agri.exception.ReviewNotFoundException;
import com.rent.agri.exception.UserNotFoundException;
import com.rent.agri.model.dto.ReviewRequest;
import com.rent.agri.model.dto.ReviewResponse;
import com.rent.agri.model.entity.Equipment;
import com.rent.agri.model.entity.Review;
import com.rent.agri.model.entity.User;
import com.rent.agri.repository.EquipmentRepository;
import com.rent.agri.repository.ReviewRepository;
import com.rent.agri.repository.UserRepository;
import com.rent.agri.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private EquipmentRepository equipmentRepository;
   
    @Autowired
    private UserRepository userRepository;

    @Override
    public ReviewResponse createReview(ReviewRequest req) {
    	Equipment existing = equipmentRepository.findById(req.getEqId())
                .orElseThrow(() -> new RuntimeException("Equipment not found with id " + req.getEqId()));
    	Review review=Review.builder()
    			.comment(req.getComment())
    			.rating(req.getRating())
    			.time(LocalDateTime.now())
    			.build();
    	
    	User user = userRepository.findById(req.getUserId())
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + req.getUserId()));

    	review.setUser(user);
    	review.setEquipment(existing);
    	
    	Review saved = reviewRepository.save(review);
        return mapToResponse(saved);
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id " + id)); 
        return mapToResponse(existing);
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
       List<Review> list = reviewRepository.findAll();
       if(list.isEmpty())
    	   throw  new ReviewNotFoundException("Review not found with id ");
       List<ReviewResponse> reviewList = list.stream().map(this::mapToResponse).collect(Collectors.toList());
       return reviewList;
    }



    @Override
    public void deleteReview(Long id) {
    	Review existing = reviewRepository.findById(id)
        .orElseThrow(() -> new ReviewNotFoundException("Review not found with id " + id));
    	existing.setUser(null);
    	existing.setEquipment(null);
    	reviewRepository.deleteById(id);
    }
    
    private ReviewResponse mapToResponse(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getReviewId())
                .comment(review.getComment())
                .time(review.getTime())
                .rating(review.getRating())
                .build();
    }

    
}
