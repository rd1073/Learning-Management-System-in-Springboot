package com.example.lms.controller;

import com.example.lms.security.JwtTokenUtil;
import com.example.lms.service.EmployeeService;
import com.example.lms.service.MentorService;

import jakarta.validation.Valid;

import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.dto.MentorUpdateRequest;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    private final MentorService mentorService;

    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("add-mentor")
    public ResponseEntity<?> createMentor(@RequestBody MentorCreationRequest request) {
        try{
        MentorDetail createdMentor = mentorService.createMentor(
                request
        );
        return new ResponseEntity<>(createdMentor, HttpStatus.CREATED);
        
    } catch (Exception e) {
        return new ResponseEntity<>("Failed to create mentor: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}











 
    // Endpoint to fully update mentor details by employee ID
    @PatchMapping("/update/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MentorDetail> updateMentorDetails(@PathVariable Long employeeId,
                                                             @RequestBody MentorUpdateRequest request) {
        MentorDetail updatedMentor = mentorService.updateMentorDetails(employeeId, request);
        return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
    }
 

 
 
}