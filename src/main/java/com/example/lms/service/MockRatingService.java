package com.example.lms.service;

import com.example.lms.dto.MockRatingDTO;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MockDetails;
import com.example.lms.entity.MockRating;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MockDetailsRepository;
import com.example.lms.repository.MockRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MockRatingService {

    @Autowired
    private MockDetailsRepository mockDetailsRepository;

    @Autowired
    private EmployeePrimaryInformationRepository employeeRepository;

    @Autowired
    private MockRatingRepository mockRatingRepository;

    @Transactional
public MockRating addMockRating(MockRatingDTO mockRatingDTO) {
    // Fetch MockDetails by mockNo
    MockDetails mockDetails = mockDetailsRepository.findById(mockRatingDTO.getMockNo())
            .orElseThrow(() -> new IllegalArgumentException("Mock not found with id: " + mockRatingDTO.getMockNo()));

    // Fetch Employee details by employeeId
    EmployeePrimaryInformation employee = employeeRepository.findById(mockRatingDTO.getEmployeeId())
            .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + mockRatingDTO.getEmployeeId()));

    // Create a new MockRating entity
    MockRating mockRating = new MockRating();
    mockRating.setMockNo(mockDetails.getMockNo()); // Ensure mockNo is set here
    mockRating.setEmployeeId(employee.getEmployeeId());
    mockRating.setEmployeeName(employee.getName());

    // Set panel details from MockDetails
    mockRating.setPanel1Id(mockDetails.getPanel1Id());
    mockRating.setPanel1Name(mockDetails.getPanel1Name());
    mockRating.setPanel2Id(mockDetails.getPanel2Id());
    mockRating.setPanel2Name(mockDetails.getPanel2Name());

    // Set other fields from the request body
    mockRating.setNotes(mockRatingDTO.getNotes());
    mockRating.setPracticalKnowledgeScore(mockRatingDTO.getPracticalKnowledgeScore());
    mockRating.setTheoreticalKnowledgeScore(mockRatingDTO.getTheoreticalKnowledgeScore());
    mockRating.setOverallFeedback(mockRatingDTO.getOverallFeedback());
    mockRating.setDetailedFeedback(mockRatingDTO.getDetailedFeedback());

    // Save the MockRating entity
    return mockRatingRepository.save(mockRating);
}

}
