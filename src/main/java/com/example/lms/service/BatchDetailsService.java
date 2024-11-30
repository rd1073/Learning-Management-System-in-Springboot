
/*
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

}*/


package com.example.lms.service;

import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeeBatch;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;
import com.example.lms.Exceptions.ResourceNotFoundException;
import com.example.lms.repository.BatchDetailsRepository;
import com.example.lms.repository.EmployeeBatchRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchDetailsService implements BatchDetailsServiceInterface {

    @Autowired
    private BatchDetailsRepository batchDetailsRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private EmployeePrimaryInformationRepository employeePrimaryInformationRepository;

    @Autowired
    private EmployeeBatchRepository employeeBatchRepository;

    @Override
    public BatchDetails createBatch(BatchDetails batchDetails) {
        MentorDetail mentor = mentorRepository
                .findById(batchDetails.getMentorId())
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with ID: " + batchDetails.getMentorId()));

        EmployeePrimaryInformation employee = employeePrimaryInformationRepository
                .findById(mentor.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + mentor.getEmployeeId()));

        batchDetails.setMentorName(employee.getName());
        return batchDetailsRepository.save(batchDetails);
    }

    @Override
    public BatchDetails getBatchById(Long batchId) {
        return batchDetailsRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with ID: " + batchId));
    }

    @Override
    public List<EmployeeBatch> getBatchesByEmployeeId(Long employeeId) {
        List<EmployeeBatch> employeeBatches = employeeBatchRepository.findByEmployeeId(employeeId);
        if (employeeBatches.isEmpty()) {
            throw new ResourceNotFoundException("No batches found for Employee ID: " + employeeId);
        }
        return employeeBatches;
    }

    @Override
    public List<BatchDetails> getBatchesByMentorId(Long mentorId) {
        List<BatchDetails> batches = batchDetailsRepository.findByMentorId(mentorId);
        if (batches.isEmpty()) {
            throw new ResourceNotFoundException("No batches found for Mentor ID: " + mentorId);
        }
        return batches;
    }

    @Override
    public List<EmployeePrimaryInformation> getEmployeesInBatch(Long batchId) {
        List<EmployeeBatch> employeeBatchList = employeeBatchRepository.findByBatchId(batchId);
        if (employeeBatchList.isEmpty()) {
            throw new ResourceNotFoundException("No employees found for Batch ID: " + batchId);
        }

        List<Long> employeeIds = employeeBatchList.stream()
                .map(EmployeeBatch::getEmployeeId)
                .collect(Collectors.toList());

        return employeePrimaryInformationRepository.findAllById(employeeIds);
    }

    @Override
    public BatchDetails updateBatch(Long batchId, BatchDetails batchDetails) {
        BatchDetails existingBatch = batchDetailsRepository
                .findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with ID: " + batchId));

        MentorDetail mentor = mentorRepository
                .findById(batchDetails.getMentorId())
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with ID: " + batchDetails.getMentorId()));

        EmployeePrimaryInformation employee = employeePrimaryInformationRepository
                .findById(mentor.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + mentor.getEmployeeId()));

        existingBatch.setBatchName(batchDetails.getBatchName());
        existingBatch.setTechnologies(batchDetails.getTechnologies());
        existingBatch.setMentorId(batchDetails.getMentorId());
        existingBatch.setMentorName(employee.getName());
        existingBatch.setStatus(batchDetails.getStatus());

        return batchDetailsRepository.save(existingBatch);
    }

    @Override
    public void deleteBatch(Long batchId) {
        BatchDetails existingBatch = batchDetailsRepository
                .findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with ID: " + batchId));
        batchDetailsRepository.delete(existingBatch);
    }
}
