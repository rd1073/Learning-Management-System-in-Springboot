

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





    public BatchDetails updateBatch(Long batchId, BatchDetails batchDetails) {
        // Fetch the existing batch using the batch ID
        BatchDetails existingBatch = batchDetailsRepository
                .findById(batchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid batch ID: " + batchId));

        // Fetch mentor details using the mentor ID
        MentorDetail mentor = mentorRepository
                .findById(batchDetails.getMentorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid mentor ID: " + batchDetails.getMentorId()));

        // Fetch employee details using the employee ID from the mentor record
        EmployeePrimaryInformation employee = employeePrimaryInformationRepository
                .findById(mentor.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + mentor.getEmployeeId()));

        // Update batch details
        existingBatch.setBatchName(batchDetails.getBatchName());
        existingBatch.setTechnologies(batchDetails.getTechnologies());
        existingBatch.setMentorId(batchDetails.getMentorId());
        existingBatch.setMentorName(employee.getName());
        existingBatch.setStatus(batchDetails.getStatus());

        // Save the updated batch details
        return batchDetailsRepository.save(existingBatch);
    }


    public void deleteBatch(Long batchId) {
        // Check if the batch exists before attempting to delete
        BatchDetails existingBatch = batchDetailsRepository
                .findById(batchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid batch ID: " + batchId));

        // Delete the batch
        batchDetailsRepository.delete(existingBatch);
    }

    // Existing method for creating a batch...

}
