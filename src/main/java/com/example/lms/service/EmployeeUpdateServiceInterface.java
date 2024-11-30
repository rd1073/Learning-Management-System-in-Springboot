package com.example.lms.service;

import java.util.List;

import com.example.lms.dto.EmployeeUpdateRequest;
import com.example.lms.entity.*;

public interface EmployeeUpdateServiceInterface {

    void updateEmployeeDetails(Long employeeId, EmployeeUpdateRequest updateRequest);

    List<EmployeeEducationInfo> addEducation(Long employeeId, List<EmployeeEducationInfo> educationInfos);

    List<EmployeeExperienceInfo> addExperience(Long employeeId, List<EmployeeExperienceInfo> experienceInfos);

    List<EmployeeContactInfo> addContact(Long employeeId, List<EmployeeContactInfo> contactInfos);

    List<EmployeeTechnicalSkillsInfo> addTechnicalSkills(Long employeeId, List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos);

    void deleteEmployee(Long employeeId);
}
