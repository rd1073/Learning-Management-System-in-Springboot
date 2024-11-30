package com.example.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dto.MockDetailsDTO;
import com.example.lms.entity.MockDetails;
import com.example.lms.service.MockDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/mock")
public class MockDetailsController {

    @Autowired
    private MockDetailsService mockDetailsService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> createMock(@RequestBody MockDetailsDTO mockDetailsDTO) {
        try {
            MockDetails createdMock = mockDetailsService.createMock(mockDetailsDTO);
            return ResponseEntity.ok(createdMock);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Entity not found: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
