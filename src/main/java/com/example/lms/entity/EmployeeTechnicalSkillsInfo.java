package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_technicalskills_info")
public class EmployeeTechnicalSkillsInfo {

    
    //private Long id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;

    public enum SkillLevel {
        BEGINNER,
        INTERMEDIATE,
        EXPERT
    }

    // Getters and Setters
     

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }
}
