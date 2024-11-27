package com.example.lms.dto;

import lombok.Data;
import java.util.List;
import com.example.lms.entity.*;

@Data
public class MentorUpdateRequest {
    private Long employeeId;
    private EmployeePrimaryInformation primaryInfo;
    private EmployeeSecondaryInfo secondaryInfo;
    private MentorDetail mentorDetail;
    private List<EmployeeAddressInfo> addresses;
    private EmployeeBankDetails bankDetails;
    private List<EmployeeEducationInfo> educationInfos;
    private List<EmployeeContactInfo> contactInfos;
    private List<EmployeeExperienceInfo> experienceInfos;
    private List<EmployeeTechnicalSkillsInfo> technicalSkills;
}
