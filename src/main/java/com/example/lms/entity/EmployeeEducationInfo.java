package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_education_info")
public class EmployeeEducationInfo {

   
    //private Long id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private String degree;

    @Column(nullable = false)
    private String institution;

    @Column(name = "year_of_passing", nullable = false)
    private Integer yearOfPassing;

    // Getters and Setters
    

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Integer getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(Integer yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }
}
