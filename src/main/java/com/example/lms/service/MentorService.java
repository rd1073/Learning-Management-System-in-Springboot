package com.example.lms.service;

import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.MentorDetail;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import com.example.lms.repository.MentorRepository;
import com.example.lms.security.JwtTokenUtil;

import jakarta.transaction.Transactional;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;
    private final EmployeePrimaryInformationRepository employeePrimaryInfoRepository;

    @Autowired
    public MentorService(MentorRepository mentorRepository, EmployeePrimaryInformationRepository employeePrimaryInfoRepository) {
        this.mentorRepository = mentorRepository;
        this.employeePrimaryInfoRepository = employeePrimaryInfoRepository;
    }

    @Transactional
    public MentorDetail createMentor(
            EmployeePrimaryInformation primaryInfo,
            LocalDate dateOfMentorship) {

        // Step 1: Save Employee Primary Information
        primaryInfo.setRole(EmployeePrimaryInformation.Role.MENTOR);
        EmployeePrimaryInformation savedPrimaryInfo = employeePrimaryInfoRepository.save(primaryInfo);


        // Step 3: Save Mentorship details
        MentorDetail mentorDetail = new MentorDetail();
        mentorDetail.setEmployeeId(savedPrimaryInfo.getEmployeeId());
        mentorDetail.setDateOfMentorship(dateOfMentorship);

        return mentorRepository.save(mentorDetail);
    }
}
