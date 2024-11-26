package com.example.lms.dto;

public class MockRatingDTO {
    private Long mockNo;
    private Long employeeId;
    private String employeeName;
    private Double practicalKnowledgeScore;  // Score out of 10
    private Double theoreticalKnowledgeScore; // Score out of 10
    private String overallFeedback;
    private String detailedFeedback;
    private String notes;

    // Getters and Setters
    public Long getMockNo() {
        return mockNo;
    }

    public void setMockNo(Long mockNo) {
        this.mockNo = mockNo;
    }
 

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
