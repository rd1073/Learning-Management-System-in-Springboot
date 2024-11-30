/*package com.example.lms.service;

import com.example.lms.dto.EmployeeRegistrationRequest;
import com.example.lms.entity.*;
import com.example.lms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeRegistrationService {

    private final EmployeePrimaryInformationRepository employeePrimaryInfoRepository;
    private final EmployeeSecondaryInfoRepository secondaryInfoRepository;
    private final EmployeeAddressInfoRepository addressInfoRepository;
    private final EmployeeBankDetailsRepository bankDetailsRepository;
    private final EmployeeContactInfoRepository contactInfoRepository;
    private final EmployeeEducationInfoRepository educationInfoRepository;
    private final EmployeeExperienceInfoRepository experienceInfoRepository;
    private final EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeRegistrationService(
        EmployeePrimaryInformationRepository employeePrimaryInfoRepository,
        EmployeeAddressInfoRepository addressInfoRepository,
        EmployeeBankDetailsRepository bankDetailsRepository,
        EmployeeEducationInfoRepository educationInfoRepository,
        EmployeeContactInfoRepository contactInfoRepository,
        EmployeeExperienceInfoRepository experienceInfoRepository,
        EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository,
        EmployeeSecondaryInfoRepository secondaryInfoRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.employeePrimaryInfoRepository = employeePrimaryInfoRepository;
        this.addressInfoRepository = addressInfoRepository;
        this.bankDetailsRepository = bankDetailsRepository;
        this.educationInfoRepository = educationInfoRepository;
        this.contactInfoRepository = contactInfoRepository;
        this.experienceInfoRepository = experienceInfoRepository;
        this.technicalSkillsInfoRepository = technicalSkillsInfoRepository;
        this.secondaryInfoRepository = secondaryInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerEmployee(EmployeeRegistrationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Registration request cannot be null.");
        }

        // Step 1: Save Primary Info (default approved = false)
        EmployeePrimaryInformation employeePrimaryInfo = request.getPrimaryInfo();
        employeePrimaryInfo.setApproved(false); // Default value
        employeePrimaryInfo.setRole(EmployeePrimaryInformation.Role.EMPLOYEE);

        // Validate and hash the password
        String rawPassword = request.getPassword();
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        employeePrimaryInfo.setPassword(passwordEncoder.encode(rawPassword));
        
        EmployeePrimaryInformation savedPrimaryInfo = employeePrimaryInfoRepository.save(employeePrimaryInfo);

        // Step 2: Save Secondary Info
        EmployeeSecondaryInfo secondaryInfo = request.getSecondaryInfo();
        if (secondaryInfo != null) {
            secondaryInfo.setEmployeeId(savedPrimaryInfo.getEmployeeId());
            secondaryInfoRepository.save(secondaryInfo);
        }

        // Step 3: Save Address Info
        List<EmployeeAddressInfo> addressInfos = request.getAddressInfos();
        if (addressInfos != null && !addressInfos.isEmpty()) {
            addressInfos.forEach(address -> address.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            addressInfoRepository.saveAll(addressInfos);
        }

        // Step 4: Save Bank Details
        EmployeeBankDetails bankDetails = request.getBankDetails();
        if (bankDetails != null ) {
            bankDetails.setEmployeeId(savedPrimaryInfo.getEmployeeId());
            bankDetailsRepository.save(bankDetails);
            
        }
        

        // Step 5: Save Contact Info
        List<EmployeeContactInfo> contactInfos = request.getContactInfos();
        if (contactInfos != null && !contactInfos.isEmpty()) {
            contactInfos.forEach(contact -> contact.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            contactInfoRepository.saveAll(contactInfos);
        }

        // Step 6: Save Education Info
        List<EmployeeEducationInfo> educationInfos = request.getEducationInfos();
        if (educationInfos != null && !educationInfos.isEmpty()) {
            educationInfos.forEach(education -> education.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            educationInfoRepository.saveAll(educationInfos);
        }

        // Step 7: Save Experience Info
        List<EmployeeExperienceInfo> experienceInfos = request.getExperienceInfos();
        if (experienceInfos != null && !experienceInfos.isEmpty()) {
            experienceInfos.forEach(experience -> experience.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            experienceInfoRepository.saveAll(experienceInfos);
        }

        // Step 8: Save Technical Skills Info
        List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos = request.getTechnicalSkillsInfos();
        if (technicalSkillsInfos != null && !technicalSkillsInfos.isEmpty()) {
            technicalSkillsInfos.forEach(skill -> skill.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            technicalSkillsInfoRepository.saveAll(technicalSkillsInfos);
        }
    }
}*/

package com.example.lms.service;

import com.example.lms.dto.EmployeeRegistrationRequest;
import com.example.lms.entity.*;
import com.example.lms.Exceptions.InvalidInputException;
import com.example.lms.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeRegistrationService implements EmployeeRegistrationServiceInterface {

    private final EmployeePrimaryInformationRepository employeePrimaryInfoRepository;
    private final EmployeeSecondaryInfoRepository secondaryInfoRepository;
    private final EmployeeAddressInfoRepository addressInfoRepository;
    private final EmployeeBankDetailsRepository bankDetailsRepository;
    private final EmployeeContactInfoRepository contactInfoRepository;
    private final EmployeeEducationInfoRepository educationInfoRepository;
    private final EmployeeExperienceInfoRepository experienceInfoRepository;
    private final EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeRegistrationService(
            EmployeePrimaryInformationRepository employeePrimaryInfoRepository,
            EmployeeAddressInfoRepository addressInfoRepository,
            EmployeeBankDetailsRepository bankDetailsRepository,
            EmployeeEducationInfoRepository educationInfoRepository,
            EmployeeContactInfoRepository contactInfoRepository,
            EmployeeExperienceInfoRepository experienceInfoRepository,
            EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository,
            EmployeeSecondaryInfoRepository secondaryInfoRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.employeePrimaryInfoRepository = employeePrimaryInfoRepository;
        this.addressInfoRepository = addressInfoRepository;
        this.bankDetailsRepository = bankDetailsRepository;
        this.educationInfoRepository = educationInfoRepository;
        this.contactInfoRepository = contactInfoRepository;
        this.experienceInfoRepository = experienceInfoRepository;
        this.technicalSkillsInfoRepository = technicalSkillsInfoRepository;
        this.secondaryInfoRepository = secondaryInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void registerEmployee(EmployeeRegistrationRequest request) {
        if (request == null) {
            throw new InvalidInputException("Registration request cannot be null.");
        }

        // Step 1: Save Primary Info (default approved = false)
        EmployeePrimaryInformation employeePrimaryInfo = request.getPrimaryInfo();
        if (employeePrimaryInfo == null) {
            throw new InvalidInputException("Primary information is required.");
        }
        employeePrimaryInfo.setApproved(false); // Default value
        employeePrimaryInfo.setRole(EmployeePrimaryInformation.Role.EMPLOYEE);

        // Validate and hash the password
        String rawPassword = request.getPassword();
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new InvalidInputException("Password cannot be null or blank.");
        }
        employeePrimaryInfo.setPassword(passwordEncoder.encode(rawPassword));

        EmployeePrimaryInformation savedPrimaryInfo = employeePrimaryInfoRepository.save(employeePrimaryInfo);

        // Step 2: Save Secondary Info
        EmployeeSecondaryInfo secondaryInfo = request.getSecondaryInfo();
        if (secondaryInfo != null) {
            secondaryInfo.setEmployeeId(savedPrimaryInfo.getEmployeeId());
            secondaryInfoRepository.save(secondaryInfo);
        }

        // Step 3: Save Address Info
        List<EmployeeAddressInfo> addressInfos = request.getAddressInfos();
        if (addressInfos != null && !addressInfos.isEmpty()) {
            addressInfos.forEach(address -> address.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            addressInfoRepository.saveAll(addressInfos);
        }

        // Step 4: Save Bank Details
        EmployeeBankDetails bankDetails = request.getBankDetails();
        if (bankDetails != null) {
            bankDetails.setEmployeeId(savedPrimaryInfo.getEmployeeId());
            bankDetailsRepository.save(bankDetails);
        }

        // Step 5: Save Contact Info
        List<EmployeeContactInfo> contactInfos = request.getContactInfos();
        if (contactInfos != null && !contactInfos.isEmpty()) {
            contactInfos.forEach(contact -> contact.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            contactInfoRepository.saveAll(contactInfos);
        }

        // Step 6: Save Education Info
        List<EmployeeEducationInfo> educationInfos = request.getEducationInfos();
        if (educationInfos != null && !educationInfos.isEmpty()) {
            educationInfos.forEach(education -> education.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            educationInfoRepository.saveAll(educationInfos);
        }

        // Step 7: Save Experience Info
        List<EmployeeExperienceInfo> experienceInfos = request.getExperienceInfos();
        if (experienceInfos != null && !experienceInfos.isEmpty()) {
            experienceInfos.forEach(experience -> experience.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            experienceInfoRepository.saveAll(experienceInfos);
        }

        // Step 8: Save Technical Skills Info
        List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos = request.getTechnicalSkillsInfos();
        if (technicalSkillsInfos != null && !technicalSkillsInfos.isEmpty()) {
            technicalSkillsInfos.forEach(skill -> skill.setEmployeeId(savedPrimaryInfo.getEmployeeId()));
            technicalSkillsInfoRepository.saveAll(technicalSkillsInfos);
        }
    }
}

