package com.example.lms.repository;

import com.example.lms.entity.EmployeePrimaryInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeePrimaryInformationRepository extends JpaRepository<EmployeePrimaryInformation, Long> {
    Optional<EmployeePrimaryInformation> findByUsername(String username);    
    void deleteByEmployeeId(Long employeeId);
    
    @Query("SELECT e FROM EmployeePrimaryInformation e WHERE e.employeeId = :employeeId")
EmployeePrimaryInformation findEmployeeById(@Param("employeeId") Long employeeId);

}
