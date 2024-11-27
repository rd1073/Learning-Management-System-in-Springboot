package com.example.lms.dto;

import java.util.List;

public class EmployeeBatchDTO {

    private Long batchId;
    private List<Long> employeeIds;

    // Getters and Setters
    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }
    
}
