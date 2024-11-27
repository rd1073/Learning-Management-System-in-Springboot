package com.example.lms.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
public class MockRating {

    @EmbeddedId
    private MockRatingId id;

    
    private String employeeName;

    private Long panel1Id;
    private String panel1Name;

    private Long panel2Id;
    private String panel2Name;

    
 
    private Double practicalKnowledgeScore;
    private Double theoreticalKnowledgeScore;

    private String overallFeedback;
    private String detailedFeedback;
    private String notes;


    // Getters and Setters
    public MockRatingId getId() {
        return id;
    }

    public void setId(MockRatingId id) {
        this.id = id;
    }

   
 

    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getPanel1Id() {
        return panel1Id;
    }

    public void setPanel1Id(Long panel1Id) {
        this.panel1Id = panel1Id;
    }

    public String getPanel1Name() {
        return panel1Name;
    }

    public void setPanel1Name(String panel1Name) {
        this.panel1Name = panel1Name;
    }

    public Long getPanel2Id() {
        return panel2Id;
    }

    public void setPanel2Id(Long panel2Id) {
        this.panel2Id = panel2Id;
    }

    public String getPanel2Name() {
        return panel2Name;
    }

    public void setPanel2Name(String panel2Name) {
        this.panel2Name = panel2Name;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

