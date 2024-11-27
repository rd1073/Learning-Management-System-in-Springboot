package com.example.lms.repository;

import com.example.lms.entity.EmployeeAddressInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressInfoRepository extends JpaRepository<EmployeeAddressInfo, Long> {
    void deleteByEmployeeId(Long employeeId);

    List<EmployeeAddressInfo> findByEmployeeId(Long employeeId);
}
