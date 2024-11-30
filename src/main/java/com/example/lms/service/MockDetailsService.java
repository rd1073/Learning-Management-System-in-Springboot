/*package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lms.dto.MockDetailsDTO;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;
import com.example.lms.entity.MockDetails;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MentorRepository;
import com.example.lms.repository.MockDetailsRepository;

@Service
public class MockDetailsService {

    @Autowired
    private MockDetailsRepository mockDetailsRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private EmployeePrimaryInformationRepository employeeRepository;

    @Transactional
    public MockDetails createMock(MockDetailsDTO mockDetailsDTO) {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the logged-in mentor's employee information
        EmployeePrimaryInformation loggedInEmployee = employeeRepository.findByUsername(loggedInUsername)
                .orElseThrow(() -> new IllegalArgumentException("Logged-in mentor's employee record not found"));

        MentorDetail mentor = mentorRepository.findByEmployeeId(loggedInEmployee.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Mentor details not found for the logged-in employee"));

                MentorDetail panel2Mentor = mentorRepository.findById(mockDetailsDTO.getPanel2Id())
                .orElseThrow(() -> new IllegalArgumentException("Panel 2 mentor not found"));
        
        // Use the retrieved Employee ID to find the employee details
        EmployeePrimaryInformation panel2Employee = employeeRepository.findById(panel2Mentor.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee record for Panel 2 not found"));

        // Create a new MockDetails entity
        MockDetails mockDetails = new MockDetails();
        mockDetails.setTechnology(mockDetailsDTO.getTechnology());
        mockDetails.setPanel1Id(mentor.getMentorId());  // Logged-in mentor's ID
        mockDetails.setPanel1Name(loggedInEmployee.getName());  // Logged-in mentor's name
        mockDetails.setPanel2Id(panel2Employee.getEmployeeId());  // Panel 2 ID from database
       
        mockDetails.setPanel2Name(panel2Employee.getName());

        //mockDetails.setPanel2Name(panel2Employee.getName());  // Panel 2 name from database
        mockDetails.setMockDate(mockDetailsDTO.getMockDate());
        mockDetails.setMockTime(mockDetailsDTO.getMockTime());

        // Save and return the mock details
        return mockDetailsRepository.save(mockDetails);
    }
}*/












package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lms.dto.MockDetailsDTO;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;
import com.example.lms.entity.MockDetails;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MentorRepository;
import com.example.lms.repository.MockDetailsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MockDetailsService implements MockDetailsServiceInterface {

    @Autowired
    private MockDetailsRepository mockDetailsRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private EmployeePrimaryInformationRepository employeeRepository;

    @Transactional
    @Override
    public MockDetails createMock(MockDetailsDTO mockDetailsDTO) throws Exception {
        try {
            String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

            // Fetch the logged-in mentor's employee information
            EmployeePrimaryInformation loggedInEmployee = employeeRepository.findByUsername(loggedInUsername)
                    .orElseThrow(() -> new EntityNotFoundException("Logged-in mentor's employee record not found"));

            MentorDetail mentor = mentorRepository.findByEmployeeId(loggedInEmployee.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Mentor details not found for the logged-in employee"));

            MentorDetail panel2Mentor = mentorRepository.findById(mockDetailsDTO.getPanel2Id())
                    .orElseThrow(() -> new EntityNotFoundException("Panel 2 mentor not found"));

            // Use the retrieved Employee ID to find the employee details
            EmployeePrimaryInformation panel2Employee = employeeRepository.findById(panel2Mentor.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Employee record for Panel 2 not found"));

            // Create a new MockDetails entity
            MockDetails mockDetails = new MockDetails();
            mockDetails.setTechnology(mockDetailsDTO.getTechnology());
            mockDetails.setPanel1Id(mentor.getMentorId()); // Logged-in mentor's ID
            mockDetails.setPanel1Name(loggedInEmployee.getName()); // Logged-in mentor's name
            mockDetails.setPanel2Id(panel2Employee.getEmployeeId()); // Panel 2 ID from database
            mockDetails.setPanel2Name(panel2Employee.getName());
            mockDetails.setMockDate(mockDetailsDTO.getMockDate());
            mockDetails.setMockTime(mockDetailsDTO.getMockTime());

            // Save and return the mock details
            return mockDetailsRepository.save(mockDetails);

        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Entity not found: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input data: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("An unexpected error occurred while creating the mock: " + e.getMessage(), e);
        }
    }
}

