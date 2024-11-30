package com.example.lms.service;

import com.example.lms.dto.MarkAttendanceRequest;

public interface AttendanceServiceInterface {
    void markAttendance(MarkAttendanceRequest request);
}
