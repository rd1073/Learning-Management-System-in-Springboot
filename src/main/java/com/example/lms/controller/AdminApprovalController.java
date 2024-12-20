/*package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.Exceptions.DuplicateKeyException;
import com.example.lms.entity.BatchDetails;
import com.example.lms.service.AdminApprovalService;
import com.example.lms.service.EmployeeService;
import com.example.lms.service.EmployeeUpdateService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/admin")
public class AdminApprovalController {

    @Autowired
    private final EmployeeUpdateService employeeUpdateService;
    private final AdminApprovalService adminApprovalService;

    public AdminApprovalController(EmployeeUpdateService employeeUpdateService,
            AdminApprovalService adminApprovalService) {
        this.employeeUpdateService = employeeUpdateService;
        this.adminApprovalService = adminApprovalService;

    }

    // Approve an employee
    @PutMapping("/approve/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> approveEmployee(@PathVariable Long employeeId,
            @RequestBody BatchDetails batchDetails) {
        try {
            Long batchId = batchDetails.getBatchId();
            adminApprovalService.approveEmployee(employeeId, batchId);
            return ResponseEntity.ok("Employee approved successfully.");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with ID: " + employeeId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during approval: " + e.getMessage());
        }
    }

    // Delete an employee
    @DeleteMapping("/{employeeId}/delete-employee")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        try {
            employeeUpdateService.deleteEmployee(employeeId);
            return ResponseEntity.ok("Employee deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with ID: " + employeeId);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error deleting employee: " + e.getMessage());
        }
    }
}*/

 package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.Exceptions.DuplicateKeyException;
import com.example.lms.Exceptions.InvalidInputException;
import com.example.lms.Exceptions.ResourceNotFoundException;
import com.example.lms.entity.BatchDetails;
import com.example.lms.service.AdminApprovalService;
import com.example.lms.service.EmployeeUpdateService;

@RestController
@RequestMapping("/api/admin")
public class AdminApprovalController {

    @Autowired
    private final EmployeeUpdateService employeeUpdateService;
    private final AdminApprovalService adminApprovalService;

    public AdminApprovalController(EmployeeUpdateService employeeUpdateService,
                                   AdminApprovalService adminApprovalService) {
        this.employeeUpdateService = employeeUpdateService;
        this.adminApprovalService = adminApprovalService;
    }

    // Approve an employee
    @PutMapping("/approve/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> approveEmployee(@PathVariable Long employeeId,
                                                   @RequestBody BatchDetails batchDetails) {
        try {
            Long batchId = batchDetails.getBatchId();
            if (batchId == null) {
                throw new InvalidInputException("Batch ID cannot be null.");
            }
            adminApprovalService.approveEmployee(employeeId, batchId);
            return ResponseEntity.ok("Employee approved successfully.");
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("Employee already exists in the batch.");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Employee or Batch not found.");
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during approval: " + e.getMessage());
        }
    }

    // Delete an employee
    @DeleteMapping("/{employeeId}/delete-employee")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        try {
            employeeUpdateService.deleteEmployee(employeeId);
            return ResponseEntity.ok("Employee deleted successfully.");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting employee: " + e.getMessage());
        }
    }
}

 