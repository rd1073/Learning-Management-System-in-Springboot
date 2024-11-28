package com.example.lms.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Attendance;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MockDetails;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {

    public byte[] exportEmployeeData(List<EmployeePrimaryInformation> employees) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Employee ID", "Name", "Username", "Email", "Role", "Approved", "Reason"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Data Rows
        int rowIndex = 1;
        for (EmployeePrimaryInformation employee : employees) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(employee.getEmployeeId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getUsername());
            row.createCell(3).setCellValue(employee.getEmail());
            row.createCell(4).setCellValue(employee.getRole().name());
            row.createCell(5).setCellValue(employee.getApproved());
            row.createCell(6).setCellValue(employee.getReason() != null ? employee.getReason() : "");
        }

        return writeWorkbookToByteArray(workbook);
    }

    public byte[] exportAttendanceData(List<Attendance> attendanceList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Attendance");

        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Attendance ID", "Employee ID", "Status", "Remarks", "Date"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Data Rows
        int rowIndex = 1;
        for (Attendance attendance : attendanceList) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(attendance.getAttendanceId());
            row.createCell(1).setCellValue(attendance.getEmployeeId());
            row.createCell(2).setCellValue(attendance.getStatus());
            row.createCell(3).setCellValue(attendance.getRemarks() != null ? attendance.getRemarks() : "");
            row.createCell(4).setCellValue(attendance.getDate().toString());
        }

        return writeWorkbookToByteArray(workbook);
    }

    public byte[] exportMockData(List<MockDetails> mockDetailsList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Mock Details");

        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Mock ID", "Technology", "Panel 1 Name", "Panel 2 Name", "Mock Date", "Mock Time"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Data Rows
        int rowIndex = 1;
        for (MockDetails mock : mockDetailsList) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(mock.getMockNo());
            row.createCell(1).setCellValue(mock.getTechnology());
            row.createCell(2).setCellValue(mock.getPanel1Name());
            row.createCell(3).setCellValue(mock.getPanel2Name());
            row.createCell(4).setCellValue(mock.getMockDate().toString());
            row.createCell(5).setCellValue(mock.getMockTime().toString());
        }

        return writeWorkbookToByteArray(workbook);
    }

    private byte[] writeWorkbookToByteArray(Workbook workbook) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            workbook.close();
            return bos.toByteArray();
        }
    }
}
