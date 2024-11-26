package com.example.lms.entity;

import jakarta.persistence.*;

@Entity
public class MockRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mockNo;


    private Integer employeeId;
    private String employeeName;

    private Integer panel1Id;
    private String panel1Name;

    private Integer panel2Id;
    private String panel2Name;

    
    private String technology;

    private Double practicalKnowledgeScore;
    private Double theoreticalKnowledgeScore;

    private String overallFeedback;
    private String detailedFeedback;

    // Getters and Setters
    public Long getmockNo() {
        return mockNo;
    }

    public void setmockId(Long mockNo) {
        this.mockNo = mockNo;
    }
 

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getPanel1Id() {
        return panel1Id;
    }

    public void setPanel1Id(Integer panel1Id) {
        this.panel1Id = panel1Id;
    }

    public String getPanel1Name() {
        return panel1Name;
    }

    public void setPanel1Name(String panel1Name) {
        this.panel1Name = panel1Name;
    }

    public Integer getPanel2Id() {
        return panel2Id;
    }

    public void setPanel2Id(Integer panel2Id) {
        this.panel2Id = panel2Id;
    }

    public String getPanel2Name() {
        return panel2Name;
    }

    public void setPanel2Name(String panel2Name) {
        this.panel2Name = panel2Name;
    }

     
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Double getPracticalKnowledgeScore() {
        return practicalKnowledgeScore;
    }

    public void setPracticalKnowledgeScore(Double practicalKnowledgeScore) {
        this.practicalKnowledgeScore = practicalKnowledgeScore;
    }

    public Double getTheoreticalKnowledgeScore() {
        return theoreticalKnowledgeScore;
    }

    public void setTheoreticalKnowledgeScore(Double theoreticalKnowledgeScore) {
        this.theoreticalKnowledgeScore = theoreticalKnowledgeScore;
    }

    public String getOverallFeedback() {
        return overallFeedback;
    }

    public void setOverallFeedback(String overallFeedback) {
        this.overallFeedback = overallFeedback;
    }

    public String getDetailedFeedback() {
        return detailedFeedback;
    }

    public void setDetailedFeedback(String detailedFeedback) {
        this.detailedFeedback = detailedFeedback;
    }
}
