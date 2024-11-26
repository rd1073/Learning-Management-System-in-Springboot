package com.example.lms.dto;

import com.example.lms.entity.*;
import java.util.List;

public class EmployeeRegistrationRequest {

    private EmployeePrimaryInformation primaryInfo;
    private EmployeeSecondaryInfo secondaryInfo;
    private List<EmployeeAddressInfo> addressInfos;
    private EmployeeBankDetails bankDetails;
    private List<EmployeeContactInfo> contactInfos;
    private List<EmployeeEducationInfo> educationInfos;
    private List<EmployeeExperienceInfo> experienceInfos;
    private List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos;
    private String password;

    // Getters and Setters
    public EmployeePrimaryInformation getPrimaryInfo() {
        return primaryInfo;
    }

    public void setPrimaryInfo(EmployeePrimaryInformation primaryInfo) {
        this.primaryInfo = primaryInfo;
    }

    public EmployeeSecondaryInfo getSecondaryInfo() {
        return secondaryInfo;
    }

    public void setSecondaryInfo(EmployeeSecondaryInfo secondaryInfo) {
        this.secondaryInfo = secondaryInfo;
    }

    public List<EmployeeAddressInfo> getAddressInfos() {
        return addressInfos;
    }

    public void setAddressInfos(List<EmployeeAddressInfo> addressInfos) {
        this.addressInfos = addressInfos;
    }
    
    public EmployeeBankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(EmployeeBankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public List<EmployeeContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(List<EmployeeContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    public List<EmployeeEducationInfo> getEducationInfos() {
        return educationInfos;
    }

    public void setEducationInfos(List<EmployeeEducationInfo> educationInfos) {
        this.educationInfos = educationInfos;
    }

    public List<EmployeeExperienceInfo> getExperienceInfos() {
        return experienceInfos;
    }

    public void setExperienceInfos(List<EmployeeExperienceInfo> experienceInfos) {
        this.experienceInfos = experienceInfos;
    }

    public List<EmployeeTechnicalSkillsInfo> getTechnicalSkillsInfos() {
        return technicalSkillsInfos;
    }

    public void setTechnicalSkillsInfos(List<EmployeeTechnicalSkillsInfo> technicalSkillsInfos) {
        this.technicalSkillsInfos = technicalSkillsInfos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
