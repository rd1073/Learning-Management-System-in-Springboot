package com.example.lms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.service.AdminApprovalService;

import jakarta.persistence.EntityNotFoundException;
 

@RestController
@RequestMapping("/api/admin")
public class AdminApprovalController {

    @Autowired
    private AdminApprovalService adminApprovalService;

    @PutMapping("/approve/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> approveEmployee(@PathVariable Long employeeId) {
        try {
            adminApprovalService.approveEmployee(employeeId);
            return ResponseEntity.ok("Employee approved successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with ID: " + employeeId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during approval: " + e.getMessage());
        }
    }
}
