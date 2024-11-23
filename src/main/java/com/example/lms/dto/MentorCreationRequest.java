package com.example.lms.dto;

import lombok.Data;
import lombok.Data;
import java.time.LocalDate;

import com.example.lms.entity.EmployeePrimaryInformation;

@Data
public class MentorCreationRequest {
    private EmployeePrimaryInformation primaryInfo;
    
    private LocalDate dateOfMentorship;
}
