package com.example.lms.repository;

import com.example.lms.entity.EmployeeContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeContactInfoRepository extends JpaRepository<EmployeeContactInfo, Long> {
    void deleteByEmployeeId(Long employeeId);
    List<EmployeeContactInfo> findByEmployeeId(Long employeeId);
}
