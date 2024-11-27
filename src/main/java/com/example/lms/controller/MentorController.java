package com.example.lms.controller;

import com.example.lms.security.JwtTokenUtil;
import com.example.lms.service.EmployeeService;
import com.example.lms.service.MentorSearchService;
import com.example.lms.service.MentorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.dto.MentorUpdateRequest;
import com.example.lms.entity.EmployeeAddressInfo;
import com.example.lms.entity.EmployeeBankDetails;
import com.example.lms.entity.EmployeeContactInfo;
import com.example.lms.entity.EmployeeEducationInfo;
import com.example.lms.entity.EmployeeExperienceInfo;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.EmployeeSecondaryInfo;
import com.example.lms.entity.EmployeeTechnicalSkillsInfo;
import com.example.lms.entity.MentorDetail;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    private final MentorService mentorService;
    private final MentorSearchService mentorSearchService;


    @Autowired
    public MentorController(MentorService mentorService, MentorSearchService mentorSearchService) {
        this.mentorService = mentorService;
        this.mentorSearchService = mentorSearchService;

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("add-mentor")
    public ResponseEntity<?> createMentor(@RequestBody MentorCreationRequest request) {
        try{
        MentorDetail createdMentor = mentorService.createMentor(
                request
        );
        return new ResponseEntity<>(createdMentor, HttpStatus.CREATED);
        
    } catch (Exception e) {
        return new ResponseEntity<>("Failed to create mentor: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }



    
}


@GetMapping("/search/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> getMentorDetails(@PathVariable Long employeeId) {
        Map<String, Object> mentorDetails = mentorSearchService.getMentorDetails(employeeId);
        return ResponseEntity.ok(mentorDetails);
    }



    // Fetch Primary Information
    @GetMapping("/search/{employeeId}/primary-info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<EmployeePrimaryInformation> getPrimaryInfo(@PathVariable Long employeeId) {
        EmployeePrimaryInformation primaryInfo = mentorSearchService.getPrimaryInfo(employeeId);
        return ResponseEntity.ok(primaryInfo);
    }

    // Fetch Address Information
    @GetMapping("/search/{employeeId}/address")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeAddressInfo>> getAddressInfo(@PathVariable Long employeeId) {
        List<EmployeeAddressInfo> addresses = mentorSearchService.getAddressInfo(employeeId);
        return ResponseEntity.ok(addresses);
    }

    // Fetch Bank Details
    @GetMapping("/search/{employeeId}/bank-details")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeBankDetails>> getBankDetails(@PathVariable Long employeeId) {
        List<EmployeeBankDetails> bankDetails = mentorSearchService.getBankDetails(employeeId);
        return ResponseEntity.ok(bankDetails);
    }

    // Fetch Education Information
    @GetMapping("/search/{employeeId}/education")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeEducationInfo>> getEducationInfo(@PathVariable Long employeeId) {
        List<EmployeeEducationInfo> educationInfos = mentorSearchService.getEducationInfo(employeeId);
        return ResponseEntity.ok(educationInfos);
    }

    // Fetch Contact Information
    @GetMapping("/search/{employeeId}/contact")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeContactInfo>> getContactInfo(@PathVariable Long employeeId) {
        List<EmployeeContactInfo> contactInfos = mentorSearchService.getContactInfo(employeeId);
        return ResponseEntity.ok(contactInfos);
    }

    // Fetch Experience Information
    @GetMapping("/search/{employeeId}/experience")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeExperienceInfo>> getExperienceInfo(@PathVariable Long employeeId) {
        List<EmployeeExperienceInfo> experienceInfos = mentorSearchService.getExperienceInfo(employeeId);
        return ResponseEntity.ok(experienceInfos);
    }

    // Fetch Technical Skills Information
    @GetMapping("/search/{employeeId}/technical-skills")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeTechnicalSkillsInfo>> getTechnicalSkillsInfo(@PathVariable Long employeeId) {
        List<EmployeeTechnicalSkillsInfo> technicalSkills = mentorSearchService.getTechnicalSkillsInfo(employeeId);
        return ResponseEntity.ok(technicalSkills);
    }

    // Fetch Secondary Information
    @GetMapping("/search/{employeeId}/secondary-info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<EmployeeSecondaryInfo>> getSecondaryInfo(@PathVariable Long employeeId) {
        Optional<EmployeeSecondaryInfo> secondaryInfo = mentorSearchService.getSecondaryInfo(employeeId);
        return ResponseEntity.ok(secondaryInfo);
    }





    
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@PostMapping("/{employeeId}/add-education")
public ResponseEntity<List<EmployeeEducationInfo>> addEducation(
        @PathVariable Long employeeId,
        @RequestBody List<EmployeeEducationInfo> educationInfos
) {
    for (EmployeeEducationInfo education : educationInfos) {
        if (education.getInstitution() == null || education.getInstitution().isBlank()) {
            return ResponseEntity.badRequest().body(null); // Return 400 for bad input
        }
    }
    List<EmployeeEducationInfo> addedEducation = mentorService.addEducation(employeeId, educationInfos);
    return ResponseEntity.ok(addedEducation);
}



@PostMapping("/{employeeId}/add-experience")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public ResponseEntity<List<EmployeeExperienceInfo>> addExperience(
        @PathVariable Long employeeId,
        @RequestBody List<EmployeeExperienceInfo> experienceInfos

        ) {
    for (EmployeeExperienceInfo experience : experienceInfos) {
        if (experience.getCompanyName() == null || experience.getCompanyName().isBlank()) {
            return ResponseEntity.badRequest().body(null); // Return 400 for missing company name
        }
        if (experience.getRole() == null || experience.getRole().isBlank()) {
            return ResponseEntity.badRequest().body(null); // Return 400 for missing role
        }
        if (experience.getYearsOfExperience() <= 0) {
            return ResponseEntity.badRequest().body(null); // Return 400 for invalid years of experience
        }
    }
    List<EmployeeExperienceInfo> addedExperiences = mentorService.addExperience(employeeId, experienceInfos);
    return ResponseEntity.ok(addedExperiences);
}





//delete mentor
@DeleteMapping("/delete/{employeeId}")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public ResponseEntity<String> deleteMentor(@PathVariable Long employeeId) {
    try {
        mentorService.deleteMentor(employeeId);
        return ResponseEntity.ok("Mentor deleted successfully.");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mentor not found.");
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting mentor: " + e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error occurred.");
    }
}












@PostMapping("/{employeeId}/add-contact")//LEFT
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public ResponseEntity<List<EmployeeContactInfo>> addContact(
        @PathVariable Long employeeId,
        @RequestBody List<EmployeeContactInfo> contactInfos
) {
    for (EmployeeContactInfo contact : contactInfos) {
        if (contact.getContactType() == null || contact.getContactType().isBlank()) {
            return ResponseEntity.badRequest().body(null); // Return 400 for missing contact type
        }
        if (contact.getContactValue() == null || contact.getContactValue().isBlank()) {
            return ResponseEntity.badRequest().body(null); // Return 400 for missing contact value
        }
    }
    List<EmployeeContactInfo> addedContacts = mentorService.addContact(employeeId, contactInfos);
    return ResponseEntity.ok(addedContacts);
}

@PostMapping("/{employeeId}/add-technical-skills")//LEFT
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public ResponseEntity<List<EmployeeTechnicalSkillsInfo>> addTechnicalSkills(
        @PathVariable Long employeeId,
        @RequestBody List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos
) {
    for (EmployeeTechnicalSkillsInfo skill : technicalSkillsInfos) {
        if (skill.getSkillName() == null || skill.getSkillName().isBlank()) {
            return ResponseEntity.badRequest().body(null); // Return 400 for missing skill name
        }
        if (skill.getSkillLevel() == null ) {
            return ResponseEntity.badRequest().body(null); // Return 400 for missing proficiency level
        }
    }
    List<EmployeeTechnicalSkillsInfo> addedSkills = mentorService.addTechnicalSkills(employeeId, technicalSkillsInfos);
    return ResponseEntity.ok(addedSkills);
}







 
    // Endpoint to fully update mentor details by employee ID
    @PatchMapping("/update/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MentorDetail> updateMentorDetails(@PathVariable Long employeeId,
                                                             @RequestBody MentorUpdateRequest request) {
        MentorDetail updatedMentor = mentorService.updateMentorDetails(employeeId, request);
        return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
    }
 

 
 
}