package com.example.lms.service;

import com.example.lms.dto.MockRatingDTO;
import com.example.lms.entity.MockRating;

public interface MockRatingServiceInterface {
    MockRating addMockRating(MockRatingDTO mockRatingDTO);
}
