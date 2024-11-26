package com.example.lms.controller;

import com.example.lms.dto.MarkAttendanceRequest;
import com.example.lms.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/mark")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")

    public ResponseEntity<String> markAttendance(@RequestBody MarkAttendanceRequest request) {
        attendanceService.markAttendance(request);
        return ResponseEntity.ok("Attendance marked successfully");
    }
}