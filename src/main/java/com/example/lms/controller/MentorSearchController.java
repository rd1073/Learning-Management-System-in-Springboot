package com.example.lms.controller;

import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.service.BatchDetailsService;
import com.example.lms.service.MentorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mentor")
public class MentorSearchController {

    private final MentorSearchService mentorSearchService;
    private final BatchDetailsService batchDetailsService;


    @Autowired
    public MentorSearchController(MentorSearchService mentorSearchService, BatchDetailsService batchDetailsService) {
        this.batchDetailsService = batchDetailsService;

        this.mentorSearchService = mentorSearchService;
    }



    // Endpoint to get batch details by batch ID
    @GetMapping("/batch/{batchId}")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getBatchById(@PathVariable("batchId") Long batchId) {
        try {
            BatchDetails batchDetails = batchDetailsService.getBatchById(batchId);
            if (batchDetails != null) {
                return ResponseEntity.ok(batchDetails);
            } else {
                return ResponseEntity.status(404).body("Batch not found with ID: " + batchId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching batch: " + e.getMessage());
        }
    }


    @GetMapping("/{batchId}/employees")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getEmployeesInBatch(@PathVariable("batchId") Long batchId) {
        try {
            List<EmployeePrimaryInformation> employees = batchDetailsService.getEmployeesInBatch(batchId);
            if (employees.isEmpty()) {
                return ResponseEntity.status(404).body("No employees found for batch ID: " + batchId);
            }
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching employees: " + e.getMessage());
     
       }
    }

    // Endpoint to search employee details by employee ID
    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<Map<String, Object>> getEmployeeDetails(@PathVariable("employeeId") Long employeeId) {
        try {
            Map<String, Object> mentorDetails = mentorSearchService.getMentorDetails(employeeId);
            return ResponseEntity.ok(mentorDetails);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    // Optionally, you can have individual endpoints for each detail type if needed.
    @GetMapping("/employee/{employeeId}/primary-info")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getPrimaryInfo(@PathVariable("employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(mentorSearchService.getPrimaryInfo(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/employee/{employeeId}/address")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getAddressInfo(@PathVariable("employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(mentorSearchService.getAddressInfo(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/employee/{employeeId}/bank-details")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getBankDetails(@PathVariable("employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(mentorSearchService.getBankDetails(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/employee/{employeeId}/education-info")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getEducationInfo(@PathVariable("employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(mentorSearchService.getEducationInfo(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/employee/{employeeId}/contact-info")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getContactInfo(@PathVariable("employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(mentorSearchService.getContactInfo(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/employee/{employeeId}/experience-info")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getExperienceInfo(@PathVariable("employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(mentorSearchService.getExperienceInfo(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/employee/{employeeId}/technical-skills-info")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getTechnicalSkillsInfo(@PathVariable("employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(mentorSearchService.getTechnicalSkillsInfo(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/employee/{employeeId}/secondary-info")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<?> getSecondaryInfo(@PathVariable("employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(mentorSearchService.getSecondaryInfo(employeeId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }



    

}