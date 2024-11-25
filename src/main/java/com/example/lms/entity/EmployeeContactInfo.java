package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_contact_info")
public class EmployeeContactInfo {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; 

  
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "contact_type", nullable = false)
    private String contactType;

    @Column(name = "contact_value", nullable = false)
    private String contactValue;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }
}