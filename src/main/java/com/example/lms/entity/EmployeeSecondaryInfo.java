package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employee_secondary_info")
@Data
public class EmployeeSecondaryInfo {
    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Column(name = "marital_status", nullable = false)
    private String maritalStatus;

    // Getters and Setters (if not using Lombok)
}