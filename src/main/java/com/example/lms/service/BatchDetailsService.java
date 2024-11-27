

package com.example.lms.service;

import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeeBatch;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;
import com.example.lms.repository.BatchDetailsRepository;
import com.example.lms.repository.EmployeeBatchRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MentorRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private EmployeeBatchRepository employeeBatchRepository;

   
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


    public BatchDetails getBatchById(Long batchId) {
        return batchDetailsRepository.findByBatchId(batchId);
                //.orElseThrow(() -> new IllegalArgumentException("Batch not found with ID: " + batchId));
    }

public List<EmployeeBatch> getBatchesByEmployeeId(Long employeeId) {
        return employeeBatchRepository.findByEmployeeId(employeeId);
    }


    public List<BatchDetails> getBatchesByMentorId(Long mentorId) {
        return batchDetailsRepository.findByMentorId(mentorId);
    }


    public List<EmployeePrimaryInformation> getEmployeesInBatch(Long batchId) {
        // Fetch all EmployeeBatch entries for the given batch ID
        List<EmployeeBatch> employeeBatchList = employeeBatchRepository.findByBatchId(batchId);

        // Extract employee IDs from the EmployeeBatch entries
        List<Long> employeeIds = employeeBatchList.stream()
                .map(EmployeeBatch::getEmployeeId)
                .collect(Collectors.toList());

        // Fetch and return the primary information for all employees in the batch
        return employeePrimaryInformationRepository.findAllById(employeeIds);
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
