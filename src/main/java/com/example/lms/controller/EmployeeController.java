package com.example.lms.controller;

import com.example.lms.entity.Employee;
import com.example.lms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Employee employee) {
        employeeService.registerAdmin(employee);
        return ResponseEntity.ok("Admin registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String result = employeeService.authenticateAdmin(username, password);
        return ResponseEntity.ok(result);
    }
}
