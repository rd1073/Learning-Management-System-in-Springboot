package com.example.lms.controller;

 
/* com.example.lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "Welcome to the Admin Dashboard!";
    }

    @GetMapping("/add-mentor")
        //@PreAuthorize("hasRole('ROLE_ADMIN')")
        //@PreAuthorize("hasRole('ROLE_ADMIN')")
       
        public String getProtectedData() {
            return "This is a protected endpoint accessible only by admins to add mentors";
        }

     
}*/
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
    @PostMapping("/add-mentor")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addMentor() {
        // Logic to add mentor
        return "Mentor added successfully!";
    }

    // Example: Restricted admin actions
    @PostMapping("/manage")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String manageAdminTasks() {
        return "Admin task executed successfully.";
    }
}


