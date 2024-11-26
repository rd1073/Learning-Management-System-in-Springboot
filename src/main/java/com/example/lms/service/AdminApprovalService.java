package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.repository.EmployeePrimaryInformationRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminApprovalService {

    @Autowired
    private EmployeePrimaryInformationRepository primaryInfoRepository;

    public void approveEmployee(Long employeeId) {
        EmployeePrimaryInformation primaryInfo = primaryInfoRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        primaryInfo.setApproved(true);
        primaryInfoRepository.save(primaryInfo);
    }
}
