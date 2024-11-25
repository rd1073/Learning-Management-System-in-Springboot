package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lms.entity.EmployeeSecondaryInfo;

@Repository
public interface EmployeeSecondaryInfoRepository extends JpaRepository<EmployeeSecondaryInfo, Long> {
}