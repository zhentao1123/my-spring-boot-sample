package com.example.demo.service.dao;

import com.example.demo.domain.Rating;

public interface ReviewsSummary {

	long getNumberOfReviewsWithRating(Rating rating);

}
