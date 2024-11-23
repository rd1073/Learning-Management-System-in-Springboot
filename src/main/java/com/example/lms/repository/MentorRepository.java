package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.MentorDetail;

@Repository
public interface MentorRepository extends JpaRepository<MentorDetail, Long> {
}
