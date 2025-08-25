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
public class ReviewResponse {
      private Long reviewId;

    private String comment;
    private LocalDateTime time;
    private int rating;
}
