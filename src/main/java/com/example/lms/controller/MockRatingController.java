package com.example.lms.controller;

import com.example.lms.Exceptions.DuplicateKeyException;
import com.example.lms.Exceptions.InvalidInputException;
import com.example.lms.Exceptions.ResourceNotFoundException;
import com.example.lms.dto.MockRatingDTO;
import com.example.lms.entity.MockRating;
import com.example.lms.service.MockRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mock-rating")
public class MockRatingController {

    @Autowired
    private MockRatingService mockRatingService;

    
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<MockRating> addMockRating(@Validated @RequestBody MockRatingDTO mockRatingDTO) {
        try {
            MockRating mockRating = mockRatingService.addMockRating(mockRatingDTO);
            return ResponseEntity.ok(mockRating);
        } catch (ResourceNotFoundException |  InvalidInputException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
