/*package com.example.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.EmployeeBatch;
import com.example.lms.entity.EmployeeBatchId;

@Repository
public interface EmployeeBatchRepository extends JpaRepository<EmployeeBatch, EmployeeBatchId> {

    // Find all employees in a specific batch
    List<EmployeeBatch> findByIdBatchId(Long batchId);

    // Find all batches for a specific employee
    List<EmployeeBatch> findByIdEmployeeId(Long employeeId);

    // Check if a batchId and employeeId combination exists
    boolean existsById(EmployeeBatchId id);
}
*/

package com.example.lms.repository;

import com.example.lms.entity.EmployeeBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeBatchRepository extends JpaRepository<EmployeeBatch, Long> {

    // Find all employees in a specific batch
    List<EmployeeBatch> findByBatchId(Long batchId);

    // Find all batches for a specific employee
    List<EmployeeBatch> findByEmployeeId(Long employeeId);

    // Custom query to check if a combination of batchId and employeeId exists
    boolean existsByBatchIdAndEmployeeId(Long batchId, Long employeeId);

    // Custom query to delete based on batchId and employeeId
    void deleteByBatchIdAndEmployeeId(Long batchId, Long employeeId);
}
