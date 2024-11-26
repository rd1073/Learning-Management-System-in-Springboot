package com.example.lms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class MockDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mockNo;

    private String technology;

    private Long panel1Id;
    private String panel1Name;

    private Long panel2Id;
    private String panel2Name;

    private LocalDate mockDate;
    private LocalTime mockTime; // Changed from LocalDateTime to LocalTime as per requirement

    // Getters and Setters
    public Long getMockNo() {
        return mockNo;
    }

    public void setMockNo(Long mockNo) {
        this.mockNo = mockNo;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Long getPanel1Id() { // Fixed return type to Long
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

    public Long getPanel2Id() { // Fixed return type to Long
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

    public LocalDate getMockDate() {
        return mockDate;
    }

    public void setMockDate(LocalDate mockDate) {
        this.mockDate = mockDate;
    }

    public LocalTime getMockTime() { // Changed type to LocalTime
        return mockTime;
    }

    public void setMockTime(LocalTime mockTime) {
        this.mockTime = mockTime;
    }
}
