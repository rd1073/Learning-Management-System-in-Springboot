package com.example.lms.controller;

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


@RestController
@RequestMapping("/api/mock")
public class MockDetailsController {

    @Autowired
    private MockDetailsService mockDetailsService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<MockDetails> createMock(
        @RequestBody MockDetailsDTO mockDetailsDTO) {

         MockDetails createdMock = mockDetailsService.createMock(mockDetailsDTO);

        return ResponseEntity.ok(createdMock);
    }
}
