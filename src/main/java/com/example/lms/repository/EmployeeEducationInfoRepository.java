package com.example.lms.repository;

import com.example.lms.entity.EmployeeEducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeEducationInfoRepository extends JpaRepository<EmployeeEducationInfo, Long> {
    List<EmployeeEducationInfo> findByEmployeeId(Long employeeId);
}
