package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.MentorDetail;
import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<MentorDetail, Long> {
    //MentorDetail findByEmployeeId(Long employeeId); 
    Optional<MentorDetail> findByEmployeeId(Long employeeId);
    // Ensure this method exists

}
