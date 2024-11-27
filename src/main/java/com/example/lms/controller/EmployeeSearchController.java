package com.example.lms.controller;

import com.example.lms.entity.*;
import com.example.lms.service.EmployeeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee-search")
public class EmployeeSearchController {

    private final EmployeeSearchService employeeSearchService;

    @Autowired
    public EmployeeSearchController(EmployeeSearchService employeeSearchService) {
        this.employeeSearchService = employeeSearchService;
    }

    // Fetch All Employee Details
    @GetMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> getEmployeeDetails(@PathVariable Long employeeId) {
        Map<String, Object> employeeDetails = employeeSearchService.getEmployeeDetails(employeeId);
        return ResponseEntity.ok(employeeDetails);
    }

    // Fetch Primary Information
    @GetMapping("/{employeeId}/primary-info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<EmployeePrimaryInformation> getPrimaryInfo(@PathVariable Long employeeId) {
        EmployeePrimaryInformation primaryInfo = employeeSearchService.getPrimaryInfo(employeeId);
        return ResponseEntity.ok(primaryInfo);
    }

    // Fetch Address Information
    @GetMapping("/{employeeId}/address")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeAddressInfo>> getAddressInfo(@PathVariable Long employeeId) {
        List<EmployeeAddressInfo> addresses = employeeSearchService.getAddressInfo(employeeId);
        return ResponseEntity.ok(addresses);
    }

    // Fetch Bank Details
    @GetMapping("/{employeeId}/bank-details")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeBankDetails>> getBankDetails(@PathVariable Long employeeId) {
        List<EmployeeBankDetails> bankDetails = employeeSearchService.getBankDetails(employeeId);
        return ResponseEntity.ok(bankDetails);
    }

    // Fetch Education Information
    @GetMapping("/{employeeId}/education")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeEducationInfo>> getEducationInfo(@PathVariable Long employeeId) {
        List<EmployeeEducationInfo> educationInfos = employeeSearchService.getEducationInfo(employeeId);
        return ResponseEntity.ok(educationInfos);
    }

    // Fetch Contact Information
    @GetMapping("/{employeeId}/contact")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeContactInfo>> getContactInfo(@PathVariable Long employeeId) {
        List<EmployeeContactInfo> contactInfos = employeeSearchService.getContactInfo(employeeId);
        return ResponseEntity.ok(contactInfos);
    }

    // Fetch Experience Information
    @GetMapping("/{employeeId}/experience")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeExperienceInfo>> getExperienceInfo(@PathVariable Long employeeId) {
        List<EmployeeExperienceInfo> experienceInfos = employeeSearchService.getExperienceInfo(employeeId);
        return ResponseEntity.ok(experienceInfos);
    }

    // Fetch Technical Skills Information
    @GetMapping("/{employeeId}/technical-skills")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeTechnicalSkillsInfo>> getTechnicalSkillsInfo(@PathVariable Long employeeId) {
        List<EmployeeTechnicalSkillsInfo> technicalSkills = employeeSearchService.getTechnicalSkillsInfo(employeeId);
        return ResponseEntity.ok(technicalSkills);
    }

    // Fetch Secondary Information
    @GetMapping("/{employeeId}/secondary-info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<EmployeeSecondaryInfo>> getSecondaryInfo(@PathVariable Long employeeId) {
        Optional<EmployeeSecondaryInfo> secondaryInfo = employeeSearchService.getSecondaryInfo(employeeId);
        return ResponseEntity.ok(secondaryInfo);
    }

    
}
