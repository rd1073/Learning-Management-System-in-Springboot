package com.example.lms.service;

import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.entity.EmployeeBankDetails;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.EmployeeSecondaryInfo;
import com.example.lms.entity.MentorDetail;
import com.example.lms.repository.EmployeeAddressInfoRepository;
import com.example.lms.repository.EmployeeBankDetailsRepository;
import com.example.lms.repository.EmployeeContactInfoRepository;
import com.example.lms.repository.EmployeeEducationInfoRepository;
import com.example.lms.repository.EmployeeExperienceInfoRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.EmployeeSecondaryInfoRepository;
import com.example.lms.repository.EmployeeTechnicalSkillsInfoRepository;
import com.example.lms.repository.MentorRepository;
import com.example.lms.security.JwtTokenUtil;

import jakarta.transaction.Transactional;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;
    private final EmployeePrimaryInformationRepository employeePrimaryInfoRepository;
    private final EmployeeAddressInfoRepository addressInfoRepository;
    private final EmployeeBankDetailsRepository bankDetailsRepository;
    private final EmployeeEducationInfoRepository educationInfoRepository;
    private final EmployeeContactInfoRepository contactInfoRepository;
    private final EmployeeExperienceInfoRepository experienceInfoRepository;
    private final EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository;
    private final EmployeeSecondaryInfoRepository secondaryInfoRepository;
    private final PasswordEncoder passwordEncoder; // New Repository

    @Autowired
    public MentorService(MentorRepository mentorRepository, EmployeePrimaryInformationRepository employeePrimaryInfoRepository, EmployeeAddressInfoRepository addressInfoRepository,
    EmployeeBankDetailsRepository bankDetailsRepository,
    EmployeeEducationInfoRepository educationInfoRepository,
    EmployeeContactInfoRepository contactInfoRepository,
    EmployeeExperienceInfoRepository experienceInfoRepository,
    EmployeeTechnicalSkillsInfoRepository technicalSkillsInfoRepository,     
        EmployeeSecondaryInfoRepository secondaryInfoRepository,
        PasswordEncoder passwordEncoder // Inject Repository
    ) {
        this.mentorRepository = mentorRepository;
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
    public MentorDetail createMentor(
            MentorCreationRequest request) {

        // Step 1: Save Employee Primary Information
        //primaryInfo.setRole(EmployeePrimaryInformation.Role.MENTOR);
        //EmployeePrimaryInformation savedPrimaryInfo = employeePrimaryInfoRepository.save(primaryInfo);
        EmployeePrimaryInformation primaryInfo = request.getPrimaryInfo();
        primaryInfo.setRole(EmployeePrimaryInformation.Role.MENTOR);

        // Hash the password provided by the admin
        String rawPassword = request.getPassword();
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);
        primaryInfo.setPassword(hashedPassword);
        
        EmployeePrimaryInformation savedPrimaryInfo = employeePrimaryInfoRepository.save(primaryInfo);

        // Step 3: Save Mentorship details
        //MentorDetail mentorDetail = new MentorDetail();
        //mentorDetail.setEmployeeId(savedPrimaryInfo.getEmployeeId());
        //mentorDetail.setDateOfMentorship(dateOfMentorship);
        MentorDetail mentorDetail = new MentorDetail();
        mentorDetail.setEmployeeId(savedPrimaryInfo.getEmployeeId());
        mentorDetail.setDateOfMentorship(request.getDateOfMentorship());
        mentorRepository.save(mentorDetail);


        Long employeeId = savedPrimaryInfo.getEmployeeId();

        // Save Address Details
        request.getAddresses().forEach(address -> {
            address.setEmployeeId(employeeId);
            addressInfoRepository.save(address);
        });

        // Save Bank Details
        EmployeeBankDetails bankDetails = request.getBankDetails();
        bankDetails.setEmployeeId(employeeId);
        bankDetailsRepository.save(bankDetails);

        // Save Education Details
        request.getEducationInfos().forEach(education -> {
            education.setEmployeeId(employeeId);
            educationInfoRepository.save(education);
        });

        // Save Contact Details
        request.getContactInfos().forEach(contact -> {
            contact.setEmployeeId(employeeId);
            contactInfoRepository.save(contact);
        });

        // Save Experience Details
        request.getExperienceInfos().forEach(experience -> {
            experience.setEmployeeId(employeeId);
            experienceInfoRepository.save(experience);
        });

        // Save Technical Skills
        request.getTechnicalSkills().forEach(skill -> {
            skill.setEmployeeId(employeeId);
            technicalSkillsInfoRepository.save(skill);
        });

        // Step 9: Save Secondary Info
        EmployeeSecondaryInfo secondaryInfo = request.getSecondaryInfo();
        secondaryInfo.setEmployeeId(employeeId);
        secondaryInfoRepository.save(secondaryInfo);

        return mentorDetail;

        //return mentorRepository.save(mentorDetail);
    }
}
