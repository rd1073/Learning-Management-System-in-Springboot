package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.MockRating;

@Repository
public interface MockRatingRepository extends JpaRepository<MockRating, Long> {
}