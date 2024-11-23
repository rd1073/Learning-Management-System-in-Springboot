package com.example.lms.repository;

import com.example.lms.entity.EmployeeAddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressInfoRepository extends JpaRepository<EmployeeAddressInfo, Long> {
}
