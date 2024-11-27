package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.MockRating;
import com.example.lms.entity.MockRatingId;

@Repository
public interface MockRatingRepository extends JpaRepository<MockRating, MockRatingId> {

    boolean existsById(MockRatingId id); // Check if a record with the same mockNo and employeeId exists
    void deleteById(MockRatingId id);

}