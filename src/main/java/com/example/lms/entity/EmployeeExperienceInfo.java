package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_experience_info")
public class EmployeeExperienceInfo {

    
    //private Long id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String role;

    @Column(name = "years_of_experience", nullable = false)
    private Integer yearsOfExperience;

    private String notes;

    // Getters and Setters
     

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
