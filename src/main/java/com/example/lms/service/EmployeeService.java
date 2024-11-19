package com.example.lms.service;

import com.example.lms.entity.Employee;
import com.example.lms.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee registerAdmin(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(Employee.Role.Admin); // Ensure the role is set to Admin
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public String authenticateAdmin(String username, String password) {
        Optional<Employee> employeeOptional = employeeRepository.findByUsername(username);
        if (employeeOptional.isEmpty()) {
            return "User not found.";
        }

        Employee employee = employeeOptional.get();
        if (passwordEncoder.matches(password, employee.getPassword()) 
                && employee.getRole() == Employee.Role.Admin) {
            return "Admin logged in successfully. Details: " + employee.toString();
        } else {
            return "Access denied. Only admins can log in.";
        }
    }
}
