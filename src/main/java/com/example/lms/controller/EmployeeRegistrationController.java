/*package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dto.EmployeeRegistrationRequest;
import com.example.lms.service.EmployeeRegistrationService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRegistrationController {

    @Autowired
    private EmployeeRegistrationService registrationService;
    

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeRegistrationRequest request) {
        try {
            registrationService.registerEmployee(request);
            return ResponseEntity.ok("Registration successful. Awaiting admin approval.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during registration: " + e.getMessage());
        }
    }
}*/


package com.example.lms.controller;

import com.example.lms.dto.EmployeeRegistrationRequest;
import com.example.lms.Exceptions.InvalidInputException;
import com.example.lms.service.EmployeeRegistrationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRegistrationController {

    @Autowired
    private EmployeeRegistrationServiceInterface registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeRegistrationRequest request) {
        try {
            registrationService.registerEmployee(request);
            return ResponseEntity.ok("Registration successful. Awaiting admin approval.");
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during registration: " + e.getMessage());
        }
    }
}

