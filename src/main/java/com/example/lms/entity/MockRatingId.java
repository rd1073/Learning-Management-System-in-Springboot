package com.example.lms.entity;

import java.io.Serializable;
import java.util.Objects;

public class MockRatingId implements Serializable {
    private Long mockNo;
    private Long employeeId;

    // Default constructor
    public MockRatingId() {}

    // Parameterized constructor
    public MockRatingId(Long mockNo, Long employeeId) {
        this.mockNo = mockNo;
        this.employeeId = employeeId;
    }

    // Getters and setters
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

    // Override equals and hashcode for composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockRatingId that = (MockRatingId) o;
        return Objects.equals(mockNo, that.mockNo) &&
               Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mockNo, employeeId);
    }
}
