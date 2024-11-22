package com.example.lms.service;

import com.example.lms.entity.Employee;
import com.example.lms.repository.EmployeeRepository;
import com.example.lms.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        //employee.setRole(Employee.Role.ADMIN); // Ensure the role is set to Admin
        if (employee.getRole() == null) {
            throw new IllegalArgumentException("Role must be specified for the employee.");
        }
    
        // Save the employee with the provided role
        return employeeRepository.save(employee);
        //return employeeRepository.save(employee);
    }

     


    // Find user by username
    public Optional<Employee> findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

     
        public String authenticateAdmin(String username, String password) {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(password, employee.getPassword())) {
            return "Bearer " + jwtTokenUtil.generateToken(employee.getUsername(), employee.getRole().name());
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

        
        
    }

 