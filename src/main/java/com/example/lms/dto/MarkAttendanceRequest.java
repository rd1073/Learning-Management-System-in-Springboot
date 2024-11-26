package com.example.lms.dto;

import lombok.Data;
import java.util.List;

@Data
public class MarkAttendanceRequest {

    private Long mentorId; // Refers to MentorDetail ID
    private String mentorName; // Mentor's name
    private String date; // Attendance date in YYYY-MM-DD format
    private List<EmployeeAttendanceDTO> employeeAttendances;

    @Data
    public static class EmployeeAttendanceDTO {
        private Long employeeId; // Refers to EmployeePrimaryInformation ID
        private String status; // "PRESENT" or "ABSENT"
        private String remarks; // Optional remarks
    }
}
