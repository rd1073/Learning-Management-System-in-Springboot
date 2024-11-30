package com.example.lms.service;

import com.example.lms.dto.MarkAttendanceRequest;
import com.example.lms.entity.Attendance;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;
import com.example.lms.Exceptions.InvalidInputException;
import com.example.lms.Exceptions.ResourceNotFoundException;
import com.example.lms.repository.AttendanceRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MentorRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AttendanceService implements AttendanceServiceInterface {

    private final AttendanceRepository attendanceRepository;
    private final EmployeePrimaryInformationRepository employeeRepository;
    private final MentorRepository mentorRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             EmployeePrimaryInformationRepository employeeRepository,
                             MentorRepository mentorRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.mentorRepository = mentorRepository;
    }

    @Override
    @Transactional
    public void markAttendance(MarkAttendanceRequest request) {
        if (request == null || request.getEmployeeAttendances() == null || request.getEmployeeAttendances().isEmpty()) {
            throw new InvalidInputException("Invalid request: Employee attendance data cannot be null or empty");
        }

        // Get the logged-in mentor's username from the Security Context
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the logged-in mentor's employee information
        EmployeePrimaryInformation loggedInEmployee = employeeRepository.findByUsername(loggedInUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Logged-in mentor's employee record not found"));

        // Fetch the mentor detail using the employee ID
        MentorDetail mentor = mentorRepository.findByEmployeeId(loggedInEmployee.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Mentor details not found for the logged-in employee"));

        // Current date for marking attendance
        LocalDate currentDate = LocalDate.now();

        // Process each employee attendance request
        for (MarkAttendanceRequest.EmployeeAttendanceDTO employeeAttendance : request.getEmployeeAttendances()) {
            // Fetch the employee by ID
            EmployeePrimaryInformation employee = employeeRepository.findById(employeeAttendance.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeAttendance.getEmployeeId()));

            // Create and save attendance record
            Attendance attendance = new Attendance();
            attendance.setEmployeeId(employee.getEmployeeId()); // Set the employee ID directly
            attendance.setDate(currentDate);
            attendance.setStatus(employeeAttendance.getStatus().toUpperCase());
            attendance.setRemarks(employeeAttendance.getRemarks());
            attendance.setMentorName(loggedInEmployee.getName()); // Use the logged-in mentor's name
            attendance.setMentorId(mentor.getMentorId().intValue()); // Set the mentor ID

            attendanceRepository.save(attendance);
        }
    }
}






/*package com.example.lms.service;

import com.example.lms.dto.MarkAttendanceRequest;
import com.example.lms.entity.Attendance;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;
import com.example.lms.repository.AttendanceRepository;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MentorRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeePrimaryInformationRepository employeeRepository;
    private final MentorRepository mentorRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             EmployeePrimaryInformationRepository employeeRepository,
                             MentorRepository mentorRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.mentorRepository = mentorRepository;
    }

    @Transactional
    public void markAttendance(MarkAttendanceRequest request) {
        // Get the logged-in mentor's username from the Security Context
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the logged-in mentor's employee information
        EmployeePrimaryInformation loggedInEmployee = employeeRepository.findByUsername(loggedInUsername)
                .orElseThrow(() -> new IllegalArgumentException("Logged-in mentor's employee record not found"));

        // Fetch the mentor detail using the employee ID
        MentorDetail mentor = mentorRepository.findByEmployeeId(loggedInEmployee.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Mentor details not found for the logged-in employee"));

        // Current date for marking attendance
        LocalDate currentDate = LocalDate.now();

        // Process each employee attendance request
        for (MarkAttendanceRequest.EmployeeAttendanceDTO employeeAttendance : request.getEmployeeAttendances()) {
            // Fetch the employee by ID
            EmployeePrimaryInformation employee = employeeRepository.findById(employeeAttendance.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeAttendance.getEmployeeId()));

            // Create and save attendance record
            Attendance attendance = new Attendance();
            attendance.setEmployeeId(employee.getEmployeeId()); // Set the employee ID directly
            attendance.setDate(currentDate);
            attendance.setStatus(employeeAttendance.getStatus().toUpperCase());
            attendance.setRemarks(employeeAttendance.getRemarks());
            attendance.setMentorName(loggedInEmployee.getName()); // Use the logged-in mentor's name
            attendance.setMentorId(mentor.getMentorId().intValue()); // Set the mentor ID

            attendanceRepository.save(attendance);
        }
    }
} */