package com.example.lms.repository;

import com.example.lms.entity.EmployeeTechnicalSkillsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTechnicalSkillsInfoRepository extends JpaRepository<EmployeeTechnicalSkillsInfo, Long> {
    void deleteByEmployeeId(Long employeeId);
    List<EmployeeTechnicalSkillsInfo> findByEmployeeId(Long employeeId);
}
