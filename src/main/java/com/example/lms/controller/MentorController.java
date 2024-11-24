package com.example.lms.controller;

import com.example.lms.security.JwtTokenUtil;
import com.example.lms.service.EmployeeService;
import com.example.lms.service.MentorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.dto.MentorUpdateRequest;
import com.example.lms.entity.EmployeeContactInfo;
import com.example.lms.entity.EmployeeEducationInfo;
import com.example.lms.entity.EmployeeExperienceInfo;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.EmployeeTechnicalSkillsInfo;
import com.example.lms.entity.MentorDetail;

import java.util.List;

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

    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
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