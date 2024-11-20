package com.example.lms.service;

import com.example.lms.entity.Employee;
import com.example.lms.repository.EmployeeRepository;
import com.example.lms.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    // Register a new admin
    public Employee registerAdmin(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(Employee.Role.ADMIN); // Ensure the role is set to Admin
        return employeeRepository.save(employee);
    }

    // Find user by username
    public Optional<Employee> findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    // Authenticate admin login and return JWT token if successful
    /*public String authenticateAdmin(String username, String password) {
        Optional<Employee> employeeOptional = employeeRepository.findByUsername(username);
        
        if (employeeOptional.isEmpty()) {
            return "User not found.";
        }

        Employee employee = employeeOptional.get();
        
        // Verify the password
        if (passwordEncoder.matches(password, employee.getPassword())) {
            // Check if the user has the role of Admin
            if (employee.getRole() == Employee.Role.ADMIN) {
                // Generate the JWT token
                String token = jwtTokenUtil.generateToken(username, "ROLE_ADMIN");
                return token;  // Return the JWT token
            } else {
                return "Access denied. Only admins can log in.";
            }
        } else {
            return "Invalid credentials.";
        }*/

        public String authenticateAdmin(String username, String password) {
            Optional<Employee> employeeOptional = employeeRepository.findByUsername(username);
        
            if (employeeOptional.isEmpty()) {
                return "User not found.";
            }
        
            Employee employee = employeeOptional.get();
        
            // Verify the password
            if (passwordEncoder.matches(password, employee.getPassword())) {
                // Check if the user has the role of Admin
                if (employee.getRole() == Employee.Role.ADMIN) {
                    // Generate the JWT token
                    String token = jwtTokenUtil.generateToken(username, "ROLE_ADMIN");
                    return "Bearer " + token;  // Return the JWT token
                } else {
                    return "Access denied. Only admins can log in.";
                }
            } else {
                return "Invalid credentials.";
            }
        }
        
    }

 