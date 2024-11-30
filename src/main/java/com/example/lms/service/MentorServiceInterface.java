package com.example.lms.service;

import com.example.lms.dto.MentorCreationRequest;
import com.example.lms.dto.MentorUpdateRequest;
import com.example.lms.entity.EmployeeEducationInfo;
import com.example.lms.entity.EmployeeExperienceInfo;
import com.example.lms.entity.EmployeeContactInfo;
import com.example.lms.entity.EmployeeTechnicalSkillsInfo;
import com.example.lms.entity.MentorDetail;
import jakarta.transaction.Transactional;

import java.util.List;

public interface MentorServiceInterface {

    // Method to create a new mentor
    @Transactional
    MentorDetail createMentor(MentorCreationRequest request);

    // Method to update mentor details
    @Transactional
    MentorDetail updateMentorDetails(Long employeeId, MentorUpdateRequest request);

    // Method to add new education details for a mentor
    @Transactional
    List<EmployeeEducationInfo> addEducation(Long employeeId, List<EmployeeEducationInfo> educationInfos);

    // Method to add new experience details for a mentor
    @Transactional
    List<EmployeeExperienceInfo> addExperience(Long employeeId, List<EmployeeExperienceInfo> experienceInfos);

    // Method to add new contact details for a mentor
    @Transactional
    List<EmployeeContactInfo> addContact(Long employeeId, List<EmployeeContactInfo> contactInfos);

    // Method to add new technical skills for a mentor
    @Transactional
    List<EmployeeTechnicalSkillsInfo> addTechnicalSkills(Long employeeId, List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos);

    // Method to delete a mentor and all associated details
    @Transactional(rollbackOn = Exception.class)
    void deleteMentor(Long employeeId);
}
