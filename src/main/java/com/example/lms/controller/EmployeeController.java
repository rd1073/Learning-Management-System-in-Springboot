package com.example.lms.controller;

import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    //used to register an employee
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody EmployeePrimaryInformation employee) {
        employeeService.registerAdmin(employee);
        return ResponseEntity.ok("Admin registered successfully!");
    }

      

     
}
