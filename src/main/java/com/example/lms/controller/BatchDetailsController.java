/*package com.example.lms.controller;

import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.dto.MentorUpdateRequest;
import com.example.lms.entity.BatchDetails;
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
import com.example.lms.service.BatchDetailsService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/batches")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class BatchController {

    @Autowired
    private BatchDetailsService batchService;

    @PostMapping("/create")
    public ResponseEntity<BatchDetails> createBatch(@RequestBody BatchDetails batchDetails) {
        BatchDetails createdBatch = batchService.createBatch(batchDetails);
        return new ResponseEntity<>(createdBatch, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{batchId}")
    public ResponseEntity<BatchDetails> updateBatch(
            @PathVariable Long batchId,
            @RequestBody BatchDetails batchDetails) {
        BatchDetails updatedBatch = batchService.updateBatch(batchId, batchDetails);
        return ResponseEntity.ok(updatedBatch);
    }

    @DeleteMapping("/delete/{batchId}")
    public ResponseEntity<String> deleteBatch(@PathVariable Long batchId) {
        batchService.deleteBatch(batchId);
        return ResponseEntity.ok("Batch deleted successfully.");
    }
}
*/
 
package com.example.lms.controller;

import com.example.lms.entity.BatchDetails;
import com.example.lms.service.BatchDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/batches")
public class BatchDetailsController {

    @Autowired
    private BatchDetailsService batchDetailsService;

    @PostMapping("/create-batch")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BatchDetails> createBatch(@RequestBody BatchDetails batchDetails) {
        try {
            BatchDetails createdBatch = batchDetailsService.createBatch(batchDetails);
            return new ResponseEntity<>(createdBatch, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/update/{batchId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BatchDetails> updateBatch(@PathVariable Long batchId, @RequestBody BatchDetails batchDetails) {
        try {
            BatchDetails updatedBatch = batchDetailsService.updateBatch(batchId, batchDetails);
            return new ResponseEntity<>(updatedBatch, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // If batch or mentor ID is invalid
        }
    }


    @DeleteMapping("/delete/{batchId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteBatch(@PathVariable Long batchId) {
        try {
            batchDetailsService.deleteBatch(batchId);
            return new ResponseEntity<>("Batch deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Batch not found", HttpStatus.NOT_FOUND);  // If batch ID is invalid
        }
    }
}

