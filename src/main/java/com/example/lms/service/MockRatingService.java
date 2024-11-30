package com.example.lms.service;

import com.example.lms.dto.MockRatingDTO;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MockDetails;
import com.example.lms.entity.MockRating;
import com.example.lms.entity.MockRatingId;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MockDetailsRepository;
import com.example.lms.repository.MockRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MockRatingService implements MockRatingServiceInterface{

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
        MockRatingId id = new MockRatingId(mockDetails.getMockNo(), employee.getEmployeeId());
        mockRating.setId(id); // Use the composite ID

        // Set employee name and panel details from MockDetails
        mockRating.setEmployeeName(employee.getName());
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

        // Check if the record already exists, if yes, delete the old one first
        if (mockRatingRepository.existsById(id)) {
            mockRatingRepository.deleteById(id); // Delete the existing record
        }

        // Save the new MockRating entity
        return mockRatingRepository.save(mockRating);
    }
}
