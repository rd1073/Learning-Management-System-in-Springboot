package com.example.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lms.dto.EmployeeUpdateRequest;
import com.example.lms.entity.*;
import com.example.lms.repository.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EmployeeUpdateService {

    private final EmployeePrimaryInformationRepository employeePrimaryInfoRepository;
    private final EmployeeAddressInfoRepository addressInfoRepository;
    private final EmployeeBankDetailsRepository bankDetailsRepository;
    private final EmployeeEducationInfoRepository educationInfoRepository;
    private final EmployeeContactInfoRepository contactInfoRepository;
    private final EmployeeExperienceInfoRepository experienceInfoRepository;
    private final EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository;
    private final EmployeeSecondaryInfoRepository secondaryInfoRepository;

    @Autowired
    public EmployeeUpdateService(
        EmployeePrimaryInformationRepository employeePrimaryInfoRepository,
        EmployeeAddressInfoRepository addressInfoRepository,
        EmployeeBankDetailsRepository bankDetailsRepository,
        EmployeeEducationInfoRepository educationInfoRepository,
        EmployeeContactInfoRepository contactInfoRepository,
        EmployeeExperienceInfoRepository experienceInfoRepository,
        EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository,
        EmployeeSecondaryInfoRepository secondaryInfoRepository
    ) {
        this.employeePrimaryInfoRepository = employeePrimaryInfoRepository;
        this.addressInfoRepository = addressInfoRepository;
        this.bankDetailsRepository = bankDetailsRepository;
        this.educationInfoRepository = educationInfoRepository;
        this.contactInfoRepository = contactInfoRepository;
        this.experienceInfoRepository = experienceInfoRepository;
        this.technicalSkillsInfoRepository = technicalSkillsInfoRepository;
        this.secondaryInfoRepository = secondaryInfoRepository;
    }

    @Transactional
    public void updateEmployeeDetails(Long employeeId, EmployeeUpdateRequest updateRequest) {
        if (updateRequest == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        // Update Primary Information
        Optional.ofNullable(updateRequest.getPrimaryInfo()).ifPresent(primaryInfo -> {
            EmployeePrimaryInformation existingPrimaryInfo = employeePrimaryInfoRepository
                .findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee Primary Information not found for ID: " + employeeId));
                existingPrimaryInfo.setApproved(false); // Default value

                existingPrimaryInfo.setName(primaryInfo.getName());
            existingPrimaryInfo.setEmail(primaryInfo.getEmail());
            employeePrimaryInfoRepository.save(existingPrimaryInfo);
        });

        // Update Secondary Information
        Optional.ofNullable(updateRequest.getSecondaryInfo()).ifPresent(secondaryInfo -> {
            EmployeeSecondaryInfo existingSecondaryInfo = secondaryInfoRepository
                .findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee Secondary Information not found for ID: " + employeeId));
            existingSecondaryInfo.setDateOfBirth(secondaryInfo.getDateOfBirth());
            existingSecondaryInfo.setNationality(secondaryInfo.getNationality());
            existingSecondaryInfo.setMaritalStatus(secondaryInfo.getMaritalStatus());
            secondaryInfoRepository.save(existingSecondaryInfo);
        });

        

        // Update Bank Details
        Optional.ofNullable(updateRequest.getBankDetails()).ifPresent(bankDetails -> {
            EmployeeBankDetails existingBankDetails = bankDetailsRepository
                .findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Bank Details not found"));
            existingBankDetails.setAccountNumber(bankDetails.getAccountNumber());
            existingBankDetails.setBankName(bankDetails.getBankName());
            existingBankDetails.setIfscCode(bankDetails.getIfscCode());
            bankDetailsRepository.save(existingBankDetails);
        });

        

        
    }















    @Transactional
    public List<EmployeeEducationInfo> addEducation(Long employeeId, List<EmployeeEducationInfo> educationInfos) {
        for (EmployeeEducationInfo education : educationInfos) {
            if (education.getInstitution() == null || education.getInstitution().isBlank()) {
                throw new IllegalArgumentException("Institution cannot be null or blank");
            }
             
            EmployeePrimaryInformation existingPrimaryInfo = employeePrimaryInfoRepository
                    .findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee Primary Information not found for ID: " + employeeId));
 
            existingPrimaryInfo.setApproved(false); 
            employeePrimaryInfoRepository.save(existingPrimaryInfo);
            education.setEmployeeId(employeeId);
        }
        return educationInfoRepository.saveAll(educationInfos);
    }

    @Transactional
    public List<EmployeeExperienceInfo> addExperience(Long employeeId, List<EmployeeExperienceInfo> experienceInfos) {
        for (EmployeeExperienceInfo experience : experienceInfos) {
            if (experience.getCompanyName() == null || experience.getCompanyName().isBlank()) {
                throw new IllegalArgumentException("Company name cannot be null or blank");
            }
            if (experience.getRole() == null || experience.getRole().isBlank()) {
                throw new IllegalArgumentException("Role cannot be null or blank");
            }
            if (experience.getYearsOfExperience() <= 0) {
                throw new IllegalArgumentException("Years of experience must be greater than 0");
            }
            EmployeePrimaryInformation existingPrimaryInfo = employeePrimaryInfoRepository
                    .findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee Primary Information not found for ID: " + employeeId));
 
            existingPrimaryInfo.setApproved(false); 
            employeePrimaryInfoRepository.save(existingPrimaryInfo);

            experience.setEmployeeId(employeeId);
        }
        return experienceInfoRepository.saveAll(experienceInfos);
    }

    @Transactional
    public List<EmployeeContactInfo> addContact(Long employeeId, List<EmployeeContactInfo> contactInfos) {
        for (EmployeeContactInfo contact : contactInfos) {
            if (contact.getContactType() == null || contact.getContactType().isBlank()) {
                throw new IllegalArgumentException("Contact type cannot be null or blank");
            }
            if (contact.getContactValue() == null || contact.getContactValue().isBlank()) {
                throw new IllegalArgumentException("Contact value cannot be null or blank");
            }

           EmployeePrimaryInformation existingPrimaryInfo = employeePrimaryInfoRepository
                    .findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee Primary Information not found for ID: " + employeeId));
 
            existingPrimaryInfo.setApproved(false); 
            employeePrimaryInfoRepository.save(existingPrimaryInfo);
            contact.setEmployeeId(employeeId);
            
        }
        return contactInfoRepository.saveAll(contactInfos);
    }

    @Transactional
    public List<EmployeeTechnicalSkillsInfo> addTechnicalSkills(Long employeeId, List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos) {
        for (EmployeeTechnicalSkillsInfo skill : technicalSkillsInfos) {
            if (skill.getSkillName() == null || skill.getSkillName().isBlank()) {
                throw new IllegalArgumentException("Skill name cannot be null or blank");
            }
            if (skill.getSkillLevel() == null) {
                throw new IllegalArgumentException("Proficiency level cannot be null");
            }

            EmployeePrimaryInformation existingPrimaryInfo = employeePrimaryInfoRepository
                    .findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee Primary Information not found for ID: " + employeeId));
 
            existingPrimaryInfo.setApproved(false); 
            employeePrimaryInfoRepository.save(existingPrimaryInfo);
            skill.setEmployeeId(employeeId);
        }
        return technicalSkillsInfoRepository.saveAll(technicalSkillsInfos);
    }









    
    @Transactional(rollbackOn = Exception.class)
    public void deleteEmployee(Long employeeId) {
        try {
            addressInfoRepository.deleteByEmployeeId(employeeId);
            bankDetailsRepository.deleteByEmployeeId(employeeId);
            contactInfoRepository.deleteByEmployeeId(employeeId);
            educationInfoRepository.deleteByEmployeeId(employeeId);
            experienceInfoRepository.deleteByEmployeeId(employeeId);
            technicalSkillsInfoRepository.deleteByEmployeeId(employeeId);
            secondaryInfoRepository.deleteById(employeeId);
            employeePrimaryInfoRepository.deleteById(employeeId);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting employee", e);
        }
    }
}
