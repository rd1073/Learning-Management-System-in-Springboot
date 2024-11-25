package com.example.lms.repository;

import com.example.lms.entity.EmployeeBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeBankDetailsRepository extends JpaRepository<EmployeeBankDetails, Long> {
    void deleteByEmployeeId(Long employeeId);
    List<EmployeeBankDetails> findByEmployeeId(Long employeeId);
}