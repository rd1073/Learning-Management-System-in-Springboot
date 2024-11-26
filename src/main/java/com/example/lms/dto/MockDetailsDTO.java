package com.example.lms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MockDetailsDTO {
    private String technology;
    private Long panel2Id;  // ID for Panel 2
    private LocalDate mockDate;  // Mock date
    private LocalTime mockTime;  // Mock time

    // Getters and Setters
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Long getPanel2Id() {
        return panel2Id;
    }

    public void setPanel2Id(Long panel2Id) {
        this.panel2Id = panel2Id;
    }

    public LocalDate getMockDate() {
        return mockDate;
    }

    public void setMockDate(LocalDate mockDate) {
        this.mockDate = mockDate;
    }

    public LocalTime getMockTime() {
        return mockTime;
    }

    public void setMockTime(LocalTime mockTime) {
        this.mockTime = mockTime;
    }
}
