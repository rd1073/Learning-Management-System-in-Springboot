package com.example.lms.service;

import com.example.lms.entity.*;
import com.example.lms.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeSearchService implements EmployeeSearchServiceInterface {

    private final EmployeePrimaryInformationRepository primaryInfoRepository;
    private final EmployeeAddressInfoRepository addressInfoRepository;
    private final EmployeeBankDetailsRepository bankDetailsRepository;
    private final EmployeeEducationInfoRepository educationInfoRepository;
    private final EmployeeContactInfoRepository contactInfoRepository;
    private final EmployeeExperienceInfoRepository experienceInfoRepository;
    private final EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository;
    private final EmployeeSecondaryInfoRepository secondaryInfoRepository;

    @Autowired
    public EmployeeSearchService(
            EmployeePrimaryInformationRepository primaryInfoRepository,
            EmployeeAddressInfoRepository addressInfoRepository,
            EmployeeBankDetailsRepository bankDetailsRepository,
            EmployeeEducationInfoRepository educationInfoRepository,
            EmployeeContactInfoRepository contactInfoRepository,
            EmployeeExperienceInfoRepository experienceInfoRepository,
            EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository,
            EmployeeSecondaryInfoRepository secondaryInfoRepository) {
        this.primaryInfoRepository = primaryInfoRepository;
        this.addressInfoRepository = addressInfoRepository;
        this.bankDetailsRepository = bankDetailsRepository;
        this.educationInfoRepository = educationInfoRepository;
        this.contactInfoRepository = contactInfoRepository;
        this.experienceInfoRepository = experienceInfoRepository;
        this.technicalSkillsInfoRepository = technicalSkillsInfoRepository;
        this.secondaryInfoRepository = secondaryInfoRepository;
    }

     
    @Transactional
    public Map<String, Object> getEmployeeDetails(Long employeeId) {
        // Fetch primary information
        EmployeePrimaryInformation primaryInfo = primaryInfoRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        // Fetch related data from other tables
        List<EmployeeAddressInfo> addresses = addressInfoRepository.findByEmployeeId(employeeId);
        List<EmployeeBankDetails> bankDetails = bankDetailsRepository.findByEmployeeId(employeeId);
        List<EmployeeEducationInfo> educationInfos = educationInfoRepository.findByEmployeeId(employeeId);
        List<EmployeeContactInfo> contactInfos = contactInfoRepository.findByEmployeeId(employeeId);
        List<EmployeeExperienceInfo> experienceInfos = experienceInfoRepository.findByEmployeeId(employeeId);
        List<EmployeeTechnicalSkillsInfo> technicalSkills = technicalSkillsInfoRepository.findByEmployeeId(employeeId);
        EmployeeSecondaryInfo secondaryInfo = secondaryInfoRepository.findByEmployeeId(employeeId)
                .orElse(null);

        // Store all data in a map
        Map<String, Object> employeeDetails = new HashMap<>();
        employeeDetails.put("primaryInfo", primaryInfo);
        employeeDetails.put("secondaryInfo", secondaryInfo);
        employeeDetails.put("addresses", addresses);
        employeeDetails.put("bankDetails", bankDetails);
        employeeDetails.put("educationInfos", educationInfos);
        employeeDetails.put("contactInfos", contactInfos);
        employeeDetails.put("experienceInfos", experienceInfos);
        employeeDetails.put("technicalSkills", technicalSkills);

        return employeeDetails;
    }

    // Fetch from Primary Information
    public EmployeePrimaryInformation getPrimaryInfo(Long employeeId) {
        return primaryInfoRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Primary information not found for ID: " + employeeId));
    }

    // Fetch from Address Information
    public List<EmployeeAddressInfo> getAddressInfo(Long employeeId) {
        return addressInfoRepository.findByEmployeeId(employeeId);
    }

    // Fetch from Bank Details
    public List<EmployeeBankDetails> getBankDetails(Long employeeId) {
        return bankDetailsRepository.findByEmployeeId(employeeId);
    }

    // Fetch from Education Information
    public List<EmployeeEducationInfo> getEducationInfo(Long employeeId) {
        return educationInfoRepository.findByEmployeeId(employeeId);
    }

    // Fetch from Contact Information
    public List<EmployeeContactInfo> getContactInfo(Long employeeId) {
        return contactInfoRepository.findByEmployeeId(employeeId);
    }

    // Fetch from Experience Information
    public List<EmployeeExperienceInfo> getExperienceInfo(Long employeeId) {
        return experienceInfoRepository.findByEmployeeId(employeeId);
    }

    // Fetch from Technical Skills Information
    public List<EmployeeTechnicalSkillsInfo> getTechnicalSkillsInfo(Long employeeId) {
        return technicalSkillsInfoRepository.findByEmployeeId(employeeId);
    }

    // Fetch from Secondary Information
    public Optional<EmployeeSecondaryInfo> getSecondaryInfo(Long employeeId) {
        return secondaryInfoRepository.findByEmployeeId(employeeId);
    }

   
      
}