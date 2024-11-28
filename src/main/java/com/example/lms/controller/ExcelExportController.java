package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Attendance;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MockDetails;
import com.example.lms.repository.AttendanceRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MockDetailsRepository;
import com.example.lms.service.ExcelExportService;

import io.jsonwebtoken.io.IOException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExcelExportController {

    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private EmployeePrimaryInformationRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private MockDetailsRepository mockDetailsRepository;

    @GetMapping("/employees")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<byte[]> exportEmployeeData() throws Exception {
        List<EmployeePrimaryInformation> employees = employeeRepository.findAll();
        byte[] excelData = excelExportService.exportEmployeeData(employees);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }


    


    @GetMapping("/attendance")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<byte[]> exportAttendanceData() throws Exception {
        List<Attendance> attendanceList = attendanceRepository.findAll();
        byte[] excelData = excelExportService.exportAttendanceData(attendanceList);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attendance.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }

    @GetMapping("/mocks")
    @PreAuthorize("hasAuthority('ROLE_MENTOR')")
    public ResponseEntity<byte[]> exportMockData() throws Exception {
        List<MockDetails> mockDetailsList = mockDetailsRepository.findAll();
        byte[] excelData = excelExportService.exportMockData(mockDetailsList);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mocks.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }
}
