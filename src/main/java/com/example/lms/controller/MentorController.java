/*package com.example.lms.controller;

import com.example.lms.security.JwtTokenUtil;
import com.example.lms.service.BatchDetailsService;
import com.example.lms.service.EmployeeService;
import com.example.lms.service.MentorSearchService;
import com.example.lms.service.MentorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.dto.MentorUpdateRequest;
import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeeAddressInfo;
import com.example.lms.entity.EmployeeBankDetails;
import com.example.lms.entity.EmployeeBatch;
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

    
    private final BatchDetailsService batchDetailsService;

    private final MentorService mentorService;
    private final MentorSearchService mentorSearchService;


    @Autowired
    public MentorController(EmployeeService employeeService,MentorService mentorService, MentorSearchService mentorSearchService, BatchDetailsService batchDetailsService) {
        this.mentorService = mentorService;
        this.mentorSearchService = mentorSearchService;
        this.batchDetailsService = batchDetailsService;
        


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







 
     @PatchMapping("/update/{employeeId}")
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     public ResponseEntity<MentorDetail> updateMentorDetails(@PathVariable Long employeeId,
                                                              @RequestBody MentorUpdateRequest request) {
         MentorDetail updatedMentor = mentorService.updateMentorDetails(employeeId, request);
         return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
     }






      @GetMapping("/search/{batchId}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MENTOR')")

    public ResponseEntity<BatchDetails> getBatchById(@PathVariable Long batchId) {
        try {
            BatchDetails batchDetails = batchDetailsService.getBatchById(batchId);
            return new ResponseEntity<>(batchDetails, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Batch ID not found
        }
    }


    @GetMapping("/search/employee/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeBatch>> getBatchesByEmployeeId(@PathVariable Long employeeId) {
        try {
            List<EmployeeBatch> employeeBatches = batchDetailsService.getBatchesByEmployeeId(employeeId);
            return new ResponseEntity<>(employeeBatches, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Employee ID not found
        }
    }




    @GetMapping("/search/mentor/{mentorId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<List<BatchDetails>> getBatchesByMentorId(@PathVariable Long mentorId) {
        try {
            List<BatchDetails> batches = batchDetailsService.getBatchesByMentorId(mentorId);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Mentor ID not found
        }
}
 



 
}*/


package com.example.lms.controller;

import com.example.lms.security.JwtTokenUtil;
import com.example.lms.service.BatchDetailsService;
import com.example.lms.service.EmployeeService;
import com.example.lms.service.MentorSearchService;
import com.example.lms.service.MentorService;
import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.dto.MentorUpdateRequest;
import com.example.lms.entity.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.lms.Exceptions.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    private final BatchDetailsService batchDetailsService;
    private final MentorService mentorService;
    private final MentorSearchService mentorSearchService;

    @Autowired
    public MentorController(EmployeeService employeeService, MentorService mentorService, MentorSearchService mentorSearchService, BatchDetailsService batchDetailsService) {
        this.mentorService = mentorService;
        this.mentorSearchService = mentorSearchService;
        this.batchDetailsService = batchDetailsService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("add-mentor")
    public ResponseEntity<?> createMentor(@RequestBody MentorCreationRequest request) {
        try {
            MentorDetail createdMentor = mentorService.createMentor(request);
            return new ResponseEntity<>(createdMentor, HttpStatus.CREATED);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("Mentor with this ID already exists.", HttpStatus.BAD_REQUEST);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>("Invalid input: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create mentor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{employeeId}/add-education")
    public ResponseEntity<List<EmployeeEducationInfo>> addEducation(
            @PathVariable Long employeeId,
            @RequestBody List<EmployeeEducationInfo> educationInfos
    ) {
        try {
            for (EmployeeEducationInfo education : educationInfos) {
                if (education.getInstitution() == null || education.getInstitution().isBlank()) {
                    throw new InvalidInputException("Institution name cannot be blank");
                }
            }
            List<EmployeeEducationInfo> addedEducation = mentorService.addEducation(employeeId, educationInfos);
            return ResponseEntity.ok(addedEducation);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{employeeId}/add-experience")
    public ResponseEntity<List<EmployeeExperienceInfo>> addExperience(
            @PathVariable Long employeeId,
            @RequestBody List<EmployeeExperienceInfo> experienceInfos
    ) {
        try {
            for (EmployeeExperienceInfo experience : experienceInfos) {
                if (experience.getCompanyName() == null || experience.getCompanyName().isBlank()) {
                    throw new InvalidInputException("Company name cannot be blank");
                }
                if (experience.getRole() == null || experience.getRole().isBlank()) {
                    throw new InvalidInputException("Role cannot be blank");
                }
                if (experience.getYearsOfExperience() <= 0) {
                    throw new InvalidInputException("Years of experience must be greater than 0");
                }
            }
            List<EmployeeExperienceInfo> addedExperiences = mentorService.addExperience(employeeId, experienceInfos);
            return ResponseEntity.ok(addedExperiences);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteMentor(@PathVariable Long employeeId) {
        try {
            mentorService.deleteMentor(employeeId);
            return ResponseEntity.ok("Mentor deleted successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mentor not found.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting mentor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error occurred.");
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{employeeId}/add-contact")
    public ResponseEntity<List<EmployeeContactInfo>> addContact(
            @PathVariable Long employeeId,
            @RequestBody List<EmployeeContactInfo> contactInfos
    ) {
        try {
            for (EmployeeContactInfo contact : contactInfos) {
                if (contact.getContactType() == null || contact.getContactType().isBlank()) {
                    throw new InvalidInputException("Contact type cannot be blank");
                }
                if (contact.getContactValue() == null || contact.getContactValue().isBlank()) {
                    throw new InvalidInputException("Contact value cannot be blank");
                }
            }
            List<EmployeeContactInfo> addedContacts = mentorService.addContact(employeeId, contactInfos);
            return ResponseEntity.ok(addedContacts);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{employeeId}/add-technical-skills")
    public ResponseEntity<List<EmployeeTechnicalSkillsInfo>> addTechnicalSkills(
            @PathVariable Long employeeId,
            @RequestBody List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos
    ) {
        try {
            for (EmployeeTechnicalSkillsInfo skill : technicalSkillsInfos) {
                if (skill.getSkillName() == null || skill.getSkillName().isBlank()) {
                    throw new InvalidInputException("Skill name cannot be blank");
                }
                if (skill.getSkillLevel() == null) {
                    throw new InvalidInputException("Skill level cannot be null");
                }
            }
            List<EmployeeTechnicalSkillsInfo> addedSkills = mentorService.addTechnicalSkills(employeeId, technicalSkillsInfos);
            return ResponseEntity.ok(addedSkills);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/update/{employeeId}")
    public ResponseEntity<MentorDetail> updateMentorDetails(@PathVariable Long employeeId,
                                                             @RequestBody MentorUpdateRequest request) {
        try {
            MentorDetail updatedMentor = mentorService.updateMentorDetails(employeeId, request);
            return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MENTOR')")
    @GetMapping("/search/{batchId}")
    public ResponseEntity<BatchDetails> getBatchById(@PathVariable Long batchId) {
        try {
            BatchDetails batchDetails = batchDetailsService.getBatchById(batchId);
            return new ResponseEntity<>(batchDetails, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/search/employee/{employeeId}")
    public ResponseEntity<List<EmployeeBatch>> getBatchesByEmployeeId(@PathVariable Long employeeId) {
        try {
            List<EmployeeBatch> employeeBatches = batchDetailsService.getBatchesByEmployeeId(employeeId);
            return new ResponseEntity<>(employeeBatches, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/search/mentor/{mentorId}")
    public ResponseEntity<List<BatchDetails>> getBatchesByMentorId(@PathVariable Long mentorId) {
        try {
            List<BatchDetails> mentorBatches = batchDetailsService.getBatchesByMentorId(mentorId);
            return new ResponseEntity<>(mentorBatches, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
