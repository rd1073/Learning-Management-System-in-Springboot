package com.example.lms.controller;

  
 import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    // Example: Get all admin details
    @GetMapping("/details")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAdminDetails() {
        return "This endpoint is accessible only to admins.";
    }

    // Example: Add a mentor
    /*@PostMapping("/add-mentor")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addMentor() {
        // Logic to add mentor
        return "Mentor added successfully!";
    }*/

    // Example: Restricted admin actions
    @PostMapping("/manage")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String manageAdminTasks() {
        return "Admin task executed successfully.";
    }
}


