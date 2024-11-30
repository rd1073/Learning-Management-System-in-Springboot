package com.example.lms.service;

import com.example.lms.entity.BatchDetails;
import com.example.lms.entity.EmployeeBatch;
import com.example.lms.entity.EmployeePrimaryInformation;

import java.util.List;

public interface BatchDetailsServiceInterface {

    BatchDetails createBatch(BatchDetails batchDetails);

    BatchDetails getBatchById(Long batchId);

    List<EmployeeBatch> getBatchesByEmployeeId(Long employeeId);

    List<BatchDetails> getBatchesByMentorId(Long mentorId);

    List<EmployeePrimaryInformation> getEmployeesInBatch(Long batchId);

    BatchDetails updateBatch(Long batchId, BatchDetails batchDetails);

    void deleteBatch(Long batchId);
}
