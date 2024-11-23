package com.example.lms.repository;

import com.example.lms.entity.EmployeeExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeExperienceInfoRepository extends JpaRepository<EmployeeExperienceInfo, Long> {
    List<EmployeeExperienceInfo> findByEmployeeId(Long employeeId);
}
