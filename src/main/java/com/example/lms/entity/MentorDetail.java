package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "mentor_detail")
public class MentorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "mentor_id", nullable = false, updatable = false)
    private Long mentorId;
     

   
     
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "date_of_mentorship", nullable = false)
    private LocalDate dateOfMentorship;

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }
    
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDateOfMentorship() {
        return dateOfMentorship;
    }

    public void setDateOfMentorship(LocalDate dateOfMentorship) {
        this.dateOfMentorship = dateOfMentorship;
    }
     

}