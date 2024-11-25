package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lms.entity.BatchDetails;

@Repository
public interface BatchDetailsRepository extends JpaRepository<BatchDetails, Long> {
}
