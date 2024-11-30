package com.example.lms.service;

import com.example.lms.entity.BatchDetails;
import jakarta.persistence.EntityNotFoundException;

public interface AdminApprovalServiceInterface {

    
    void approveEmployee(Long employeeId, Long batchId) throws EntityNotFoundException, IllegalStateException;
}
