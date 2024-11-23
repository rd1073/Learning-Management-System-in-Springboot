package com.example.lms.dto;

import lombok.Data;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import com.example.lms.entity.EmployeeAddressInfo;
import com.example.lms.entity.EmployeeBankDetails;
import com.example.lms.entity.EmployeeContactInfo;
import com.example.lms.entity.EmployeeEducationInfo;
import com.example.lms.entity.EmployeeExperienceInfo;
import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.EmployeeSecondaryInfo;
import com.example.lms.entity.EmployeeTechnicalSkillsInfo;

@Data
public class MentorCreationRequest {
    private EmployeePrimaryInformation primaryInfo;
    
    private LocalDate dateOfMentorship;

    private List<EmployeeAddressInfo> addresses;
    private EmployeeBankDetails bankDetails;
    private List<EmployeeEducationInfo> educationInfos;
    private List<EmployeeContactInfo> contactInfos;
    private List<EmployeeExperienceInfo> experienceInfos;
    private List<EmployeeTechnicalSkillsInfo> technicalSkills;
    private EmployeeSecondaryInfo secondaryInfo; 
    private String password;
}
