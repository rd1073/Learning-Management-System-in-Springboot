package com.example.lms.repository;

import java.util.List;

import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lms.entity.BatchDetails;

@Repository
public interface BatchDetailsRepository extends JpaRepository<BatchDetails, Long> {

    BatchDetails findByBatchId(Long batchId);
    //BatchDetails findByMentorId(Long mentorId);
    List<BatchDetails> findByMentorId(Long mentorId);

    //BatchDetails deleteByEmployeeId(Long employeeId);

}
