package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.MockDetails;

@Repository
public interface MockDetailsRepository extends JpaRepository<MockDetails, Long> {
}