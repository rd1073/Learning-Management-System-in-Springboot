package com.example.lms.service;

import com.example.lms.entity.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MentorSearchServiceInterface {

    // Fetch mentor details as a map
    Map<String, Object> getMentorDetails(Long employeeId);

    // Fetch primary information
    EmployeePrimaryInformation getPrimaryInfo(Long employeeId);

    // Fetch address information
    List<EmployeeAddressInfo> getAddressInfo(Long employeeId);

    // Fetch bank details
    List<EmployeeBankDetails> getBankDetails(Long employeeId);

    // Fetch education information
    List<EmployeeEducationInfo> getEducationInfo(Long employeeId);

    // Fetch contact information
    List<EmployeeContactInfo> getContactInfo(Long employeeId);

    // Fetch experience information
    List<EmployeeExperienceInfo> getExperienceInfo(Long employeeId);

    // Fetch technical skills information
    List<EmployeeTechnicalSkillsInfo> getTechnicalSkillsInfo(Long employeeId);

    // Fetch secondary information
    Optional<EmployeeSecondaryInfo> getSecondaryInfo(Long employeeId);
}
