package com.example.lms.service;

import com.example.lms.entity.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeSearchServiceInterface {

    Map<String, Object> getEmployeeDetails(Long employeeId);

    EmployeePrimaryInformation getPrimaryInfo(Long employeeId);

    List<EmployeeAddressInfo> getAddressInfo(Long employeeId);

    List<EmployeeBankDetails> getBankDetails(Long employeeId);

    List<EmployeeEducationInfo> getEducationInfo(Long employeeId);

    List<EmployeeContactInfo> getContactInfo(Long employeeId);

    List<EmployeeExperienceInfo> getExperienceInfo(Long employeeId);

    List<EmployeeTechnicalSkillsInfo> getTechnicalSkillsInfo(Long employeeId);

    Optional<EmployeeSecondaryInfo> getSecondaryInfo(Long employeeId);
}
