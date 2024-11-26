package com.example.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.lms.dto.EmployeeUpdateRequest;
import com.example.lms.entity.*;
import com.example.lms.service.EmployeeUpdateService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeUpdateController {

    private final EmployeeUpdateService employeeUpdateService;

    @Autowired
    public EmployeeUpdateController(EmployeeUpdateService employeeUpdateService) {
        this.employeeUpdateService = employeeUpdateService;
    }

    // Update employee details
    @PatchMapping("/update/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<String> updateEmployeeDetails(
            @PathVariable Long employeeId,
            @RequestBody EmployeeUpdateRequest updateRequest) {
        try {
            employeeUpdateService.updateEmployeeDetails(employeeId, updateRequest);
            return ResponseEntity.ok("Employee details updated successfully.Waiting for Admin approval");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating employee details: " + e.getMessage());
        }
    }

    // Add education details for an employee
    @PostMapping("/add-education/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<EmployeeEducationInfo>> addEducation(
            @PathVariable Long employeeId,
            @RequestBody List<EmployeeEducationInfo> educationInfos) {
        try {
            List<EmployeeEducationInfo> savedEducationInfos = employeeUpdateService.addEducation(employeeId, educationInfos);
            return ResponseEntity.ok(savedEducationInfos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Add experience details for an employee
    @PostMapping("/add-experience/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<EmployeeExperienceInfo>> addExperience(
            @PathVariable Long employeeId,
            @RequestBody List<EmployeeExperienceInfo> experienceInfos) {
        try {
            List<EmployeeExperienceInfo> savedExperienceInfos = employeeUpdateService.addExperience(employeeId, experienceInfos);
            return ResponseEntity.ok(savedExperienceInfos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Add contact details for an employee
    @PostMapping("/add-contact/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<EmployeeContactInfo>> addContact(
            @PathVariable Long employeeId,
            @RequestBody List<EmployeeContactInfo> contactInfos) {
        try {
            List<EmployeeContactInfo> savedContactInfos = employeeUpdateService.addContact(employeeId, contactInfos);
            return ResponseEntity.ok(savedContactInfos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Add technical skills for an employee
    @PostMapping("/add-technical-skills/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<EmployeeTechnicalSkillsInfo>> addTechnicalSkills(
            @PathVariable Long employeeId,
            @RequestBody List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos) {
        try {
            List<EmployeeTechnicalSkillsInfo> savedSkillsInfos = employeeUpdateService.addTechnicalSkills(employeeId, technicalSkillsInfos);
            return ResponseEntity.ok(savedSkillsInfos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
}
