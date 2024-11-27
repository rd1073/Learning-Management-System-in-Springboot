 
 
package com.example.lms.controller;

import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeeBatch;
import com.example.lms.service.BatchDetailsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/batches")
public class BatchDetailsController {

    @Autowired
    private BatchDetailsService batchDetailsService;

    @PostMapping("/create-batch")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BatchDetails> createBatch(@RequestBody BatchDetails batchDetails) {
        try {
            BatchDetails createdBatch = batchDetailsService.createBatch(batchDetails);
            return new ResponseEntity<>(createdBatch, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/update/{batchId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BatchDetails> updateBatch(@PathVariable Long batchId, @RequestBody BatchDetails batchDetails) {
        try {
            BatchDetails updatedBatch = batchDetailsService.updateBatch(batchId, batchDetails);
            return new ResponseEntity<>(updatedBatch, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // If batch or mentor ID is invalid
        }
    }


    @DeleteMapping("/delete/{batchId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteBatch(@PathVariable Long batchId) {
        try {
            batchDetailsService.deleteBatch(batchId);
            return new ResponseEntity<>("Batch deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Batch not found", HttpStatus.NOT_FOUND);  // If batch ID is invalid
        }
    }


    @GetMapping("/search/{batchId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')

    public ResponseEntity<BatchDetails> getBatchById(@PathVariable Long batchId) {
        try {
            BatchDetails batchDetails = batchDetailsService.getBatchById(batchId);
            return new ResponseEntity<>(batchDetails, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Batch ID not found
        }
    }


    @GetMapping("/search/employee/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeBatch>> getBatchesByEmployeeId(@PathVariable Long employeeId) {
        try {
            List<EmployeeBatch> employeeBatches = batchDetailsService.getBatchesByEmployeeId(employeeId);
            return new ResponseEntity<>(employeeBatches, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Employee ID not found
        }
    }




    @GetMapping("/search/mentor/{mentorId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<List<BatchDetails>> getBatchesByMentorId(@PathVariable Long mentorId) {
        try {
            List<BatchDetails> batches = batchDetailsService.getBatchesByMentorId(mentorId);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Mentor ID not found
        }
}

}