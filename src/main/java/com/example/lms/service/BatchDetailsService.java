/*package com.example.lms.service;

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

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BatchDetailsService {

    @Autowired
    private BatchDetailsRepository batchDetailsRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private EmployeePrimaryInformationRepository employeeRepository;

    @Transactional
    public BatchDetails createBatch(BatchDetails batchDetails) {
        // Fetch mentor details
        MentorDetail mentor = mentorRepository.findById(batchDetails.getMentorId())
                .orElseThrow(() -> new EntityNotFoundException("Mentor not found with ID: " + batchDetails.getMentorId()));

        // Fetch employee details using the mentor's employeeId
        EmployeePrimaryInformation employee = employeeRepository.findById(mentor.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + mentor.getEmployeeId()));

        // Set derived fields
        batchDetails.setMentorName(employee.getName());
        batchDetails.setEmployeeId(mentor.getEmployeeId());

        // Save and return the batch
        return batchDetailsRepository.save(batchDetails);
    }

    @Transactional
    public BatchDetails updateBatch(Long batchId, BatchDetails batchDetails) {
        // Fetch the existing batch
        BatchDetails existingBatch = batchDetailsRepository.findById(batchId)
                .orElseThrow(() -> new EntityNotFoundException("Batch not found with ID: " + batchId));

        // Update fields that are part of the request body
        existingBatch.setBatchName(batchDetails.getBatchName());
        existingBatch.setTechnologies(batchDetails.getTechnologies());
        existingBatch.setStartDate(batchDetails.getStartDate());
        existingBatch.setEndDate(batchDetails.getEndDate());
        existingBatch.setStatus(batchDetails.getStatus());

        // Save and return the updated batch
        return batchDetailsRepository.save(existingBatch);
    }

    @Transactional
    public void deleteBatch(Long batchId) {
        if (!batchDetailsRepository.existsById(batchId)) {
            throw new EntityNotFoundException("Batch not found with ID: " + batchId);
        }
        batchDetailsRepository.deleteById(batchId);
    }
}
*/
 

package com.example.lms.service;

import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;
import com.example.lms.repository.BatchDetailsRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchDetailsService {

    @Autowired
    private BatchDetailsRepository batchDetailsRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private EmployeePrimaryInformationRepository employeePrimaryInformationRepository;

    /**
     * Create a new batch and set the mentor name by fetching the corresponding employee ID.
     *
     * @param batchDetails the batch details to be created
     * @return the saved batch details
     * @throws IllegalArgumentException if the mentor ID or employee ID is invalid
     */
    public BatchDetails createBatch(BatchDetails batchDetails) {
        // Fetch mentor details using the mentor ID
        MentorDetail mentor = mentorRepository
                .findById(batchDetails.getMentorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid mentor ID: " + batchDetails.getMentorId()));

        // Fetch employee details using the employee ID from the mentor record
        EmployeePrimaryInformation employee = employeePrimaryInformationRepository
                .findById(mentor.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + mentor.getEmployeeId()));

        // Set the mentor's name in the batch details
        batchDetails.setMentorName(employee.getName());

        // Save the batch details
        return batchDetailsRepository.save(batchDetails);
    }
}
