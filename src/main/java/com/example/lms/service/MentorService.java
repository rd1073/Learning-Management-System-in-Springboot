package com.example.lms.service;

import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.dto.MentorUpdateRequest;
import com.example.lms.entity.EmployeeBankDetails;
import com.example.lms.entity.EmployeeContactInfo;
import com.example.lms.entity.EmployeeEducationInfo;
import com.example.lms.entity.EmployeeExperienceInfo;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.EmployeeSecondaryInfo;
import com.example.lms.entity.EmployeeTechnicalSkillsInfo;
import com.example.lms.entity.MentorDetail;
import com.example.lms.repository.BatchDetailsRepository;
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

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MentorService implements MentorServiceInterface{

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
        primaryInfo.setApproved(true);


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
        //mentorDetail.setEmployeeId(savedPrimaryInfo.getEmployeeId());

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







    //update mentor
    @Transactional
    public MentorDetail updateMentorDetails(Long employeeId, MentorUpdateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
    
        // Update Primary Information
        Optional.ofNullable(request.getPrimaryInfo()).ifPresent(primaryInfo -> {
            EmployeePrimaryInformation existingPrimaryInfo = employeePrimaryInfoRepository
                    .findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee Primary Information not found for ID: " + employeeId));
            existingPrimaryInfo.setName(primaryInfo.getName());
            existingPrimaryInfo.setEmail(primaryInfo.getEmail());
            employeePrimaryInfoRepository.save(existingPrimaryInfo);
        });
    
        // Update Secondary Information
        Optional.ofNullable(request.getSecondaryInfo()).ifPresent(secondaryInfo -> {
            EmployeeSecondaryInfo existingSecondaryInfo = secondaryInfoRepository
                    .findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee Secondary Information not found for ID: " + employeeId));
            existingSecondaryInfo.setDateOfBirth(secondaryInfo.getDateOfBirth());
            existingSecondaryInfo.setNationality(secondaryInfo.getNationality());
            existingSecondaryInfo.setMaritalStatus(secondaryInfo.getMaritalStatus());
            secondaryInfoRepository.save(existingSecondaryInfo);
        });
    
        // Update Address Information
        if (request.getAddresses() != null) {
            addressInfoRepository.deleteByEmployeeId(employeeId);
            request.getAddresses().forEach(address -> {
                address.setEmployeeId(employeeId);
                addressInfoRepository.save(address);
            });
        }
    
        
    
        // Update Bank Details
        Optional.ofNullable(request.getBankDetails()).ifPresent(bankDetails -> {
            EmployeeBankDetails existingBankDetails = bankDetailsRepository
                    .findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Bank Details not found"));
            existingBankDetails.setAccountNumber(bankDetails.getAccountNumber());
            existingBankDetails.setBankName(bankDetails.getBankName());
            existingBankDetails.setIfscCode(bankDetails.getIfscCode());
            bankDetailsRepository.save(existingBankDetails);
        });
    
        // Update Education Information
        if (request.getEducationInfos() != null) {
            educationInfoRepository.deleteByEmployeeId(employeeId);
            request.getEducationInfos().forEach(education -> {
                education.setEmployeeId(employeeId);
                educationInfoRepository.save(education);
            });
        }
    
        // Update Contact Information
        if (request.getContactInfos() != null) {
            contactInfoRepository.deleteByEmployeeId(employeeId);
            request.getContactInfos().forEach(contact -> {
                contact.setEmployeeId(employeeId);
                contactInfoRepository.save(contact);
            });
        }
    
               /* // Update Education Details
                if (request.getEducationInfos() != null) {
                    educationInfoRepository.deleteByEmployeeId(employeeId);
                    request.getEducationInfos().forEach(education -> {
                        education.setEmployeeId(employeeId);
                        educationInfoRepository.save(education);
                    });
                }*/
        
         
    
        // Fetch and return the updated Mentor Detail
        return mentorRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Mentor not found for ID: " + employeeId));
    }


    
// add new mentor details
@Transactional
public List<EmployeeEducationInfo> addEducation(Long employeeId, List<EmployeeEducationInfo> educationInfos) {
    for (EmployeeEducationInfo education : educationInfos) {
        if (education.getInstitution() == null || education.getInstitution().isBlank()) {
            throw new IllegalArgumentException("Institution cannot be null or blank");
        }
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
        if (skill.getSkillLevel() == null ) {
            throw new IllegalArgumentException("Proficiency level cannot be null or blank");
        }
        skill.setEmployeeId(employeeId);
    }
    return technicalSkillsInfoRepository.saveAll(technicalSkillsInfos);
}











//delete

@Transactional(rollbackOn = Exception.class) // Ensure rollback in case of errors
public void deleteMentor(Long employeeId) {
    try {
        // Step 1: Delete Address Info
        addressInfoRepository.deleteByEmployeeId(employeeId);
        //BatchDetailsRepository.deleteByEmployeeId(employeeId);

        
        // Step 2: Delete Bank Details
        bankDetailsRepository.deleteByEmployeeId(employeeId);

        // Step 3: Delete Contact Info
        contactInfoRepository.deleteByEmployeeId(employeeId);

        // Step 4: Delete Education Info
        educationInfoRepository.deleteByEmployeeId(employeeId);

        // Step 5: Delete Experience Info
        experienceInfoRepository.deleteByEmployeeId(employeeId);

        // Step 6: Delete Technical Skills Info
        technicalSkillsInfoRepository.deleteByEmployeeId(employeeId);

        // Step 7: Delete Secondary Info
        secondaryInfoRepository.deleteById(employeeId);

        // Step 8: Delete Mentor Detail
        mentorRepository.deleteById(employeeId);

        // Step 9: Delete Primary Information
        employeePrimaryInfoRepository.deleteById(employeeId);
    } catch (Exception e) {
        throw new RuntimeException("Error occurred while deleting mentor", e); // Re-throw exception to ensure rollback
    }
}

}


 
