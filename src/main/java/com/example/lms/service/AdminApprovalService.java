/*package com.example.lms.service;

import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.EmployeeBatch;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.repository.BatchDetailsRepository;
import com.example.lms.repository.EmployeeBatchRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminApprovalService {

    @Autowired
    private EmployeePrimaryInformationRepository primaryInfoRepository;

    @Autowired
    private BatchDetailsRepository batchDetailsRepository;

    @Autowired
    private EmployeeBatchRepository employeeBatchRepository;


    public void approveEmployee(Long employeeId, Long batchId) {
        EmployeePrimaryInformation primaryInfo = primaryInfoRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        primaryInfo.setApproved(true);

        // Step 2: Allot the Batch to the Employee
        BatchDetailsRepository batch = batchDetailsRepository.findByBatchId(batchId)
                .orElseThrow(() -> new EntityNotFoundException("Batch not found with ID: " + batchId));

         // Create an EmployeeBatch entry
        EmployeeBatchRepository employeeBatch = new EmployeeBatch();
        employeeBatch.setEmployeeId(primaryInfo.getEmployeeId());  // Pass the employeeId
        employeeBatch.setBatchId(batch.getBatchId());  // Pass the batchId
        employeeBatch.setEmployeeName(primaryInfo.getName());  // Set employee name if needed
        employeeBatch.setBatchName(batch.getBatchName());  // Set batch name if needed

        // Save the employee-batch relation
        employeeBatchRepository.save(employeeBatch);

        // Save the updated employee info
        primaryInfoRepository.save(primaryInfo);
    }
}
*/
/*
package com.example.lms.service;

import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeeBatch;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.BatchDetailsRepository;
import com.example.lms.repository.EmployeeBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AdminApprovalService {

    @Autowired
    private EmployeePrimaryInformationRepository primaryInfoRepository;

    @Autowired
    private BatchDetailsRepository batchDetailsRepository;

    @Autowired
    private EmployeeBatchRepository employeeBatchRepository;

    // Approve an employee and add them to a batch
    public void approveEmployee(Long employeeId, Long batchId) {
        // Step 1: Fetch the EmployeePrimaryInformation object based on the employeeId
        //System.out.println("Searching for employee with ID: " + employeeId);
        EmployeePrimaryInformation primaryInfo = primaryInfoRepository.findEmployeeById(employeeId);
    //.orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));


        // Step 2: Mark the employee as approved
        primaryInfo.setApproved(true);  // Assuming you have a `setApproved` method in EmployeePrimaryInformation

        // Step 3: Fetch the BatchDetails object based on the batchId
        BatchDetails batch = batchDetailsRepository.findByBatchId(batchId);
        if (batch == null) {
            throw new EntityNotFoundException("Batch not found with ID: " + batchId);
        }

        // Step 4: Create an EmployeeBatch relation between employee and batch
        EmployeeBatch employeeBatch = new EmployeeBatch();
        employeeBatch.setEmployeeId(primaryInfo.getEmployeeId());  // Set employee ID
        employeeBatch.setBatchId(batch.getBatchId());  // Set batch ID
        employeeBatch.setEmployeeName(primaryInfo.getName());  // Assuming the employee's name exists in EmployeePrimaryInformation
        employeeBatch.setBatchName(batch.getBatchName());  // Set batch name from BatchDetails

        // Step 5: Save the EmployeeBatch record
        employeeBatchRepository.save(employeeBatch);

        // Step 6: Save the updated employee record to persist approval status
        primaryInfoRepository.save(primaryInfo);
    }
}*/


package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeeBatch;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.repository.BatchDetailsRepository;
import com.example.lms.repository.EmployeeBatchRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminApprovalService {

    @Autowired
    private EmployeePrimaryInformationRepository primaryInfoRepository;

    @Autowired
    private BatchDetailsRepository batchDetailsRepository;

    @Autowired
    private EmployeeBatchRepository employeeBatchRepository;

    public void approveEmployee(Long employeeId, Long batchId) {
        // Ensure employee exists
        EmployeePrimaryInformation primaryInfo = primaryInfoRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

       

        // Ensure batch exists
        BatchDetails batch = batchDetailsRepository.findByBatchId(batchId);
        if (batch == null) {
            throw new EntityNotFoundException("Batch not found with ID: " + batchId);
        }

        // Assuming you want to associate the employee with the batch
        EmployeeBatch employeeBatch = new EmployeeBatch();
        employeeBatch.setEmployeeId(primaryInfo.getEmployeeId());
        employeeBatch.setBatchId(batch.getBatchId());
        employeeBatch.setEmployeeName(primaryInfo.getName());  // Assuming the employee's name exists in EmployeePrimaryInformation
        employeeBatch.setBatchName(batch.getBatchName());

         // Approve the employee
         primaryInfo.setApproved(true);
         primaryInfoRepository.save(primaryInfo);
        employeeBatchRepository.save(employeeBatch); // Save the association
        

        // Optionally return success or confirmation message
        System.out.println("Employee " + employeeId + " approved and assigned to batch " + batchId);
    }
}
