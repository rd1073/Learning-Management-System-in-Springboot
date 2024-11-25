package com.example.lms.repository;

import com.example.lms.entity.EmployeePrimaryInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeePrimaryInformationRepository extends JpaRepository<EmployeePrimaryInformation, Long> {
    Optional<EmployeePrimaryInformation> findByUsername(String username);    
    void deleteByEmployeeId(Long employeeId);

}
