package com.example.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.entity.EmployeeSecondaryInfo;

@Repository
public interface EmployeeSecondaryInfoRepository extends JpaRepository<EmployeeSecondaryInfo, Long> {
    void deleteByEmployeeId(Long employeeId);

    Optional<EmployeeSecondaryInfo> findByEmployeeId(Long employeeId);


}
