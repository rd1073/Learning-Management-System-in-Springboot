package com.example.lms.controller;

import com.example.lms.security.JwtTokenUtil;
import com.example.lms.service.EmployeeService;
import com.example.lms.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final EmployeeService employeeService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationController(EmployeeService employeeService, JwtTokenUtil jwtTokenUtil) {
        this.employeeService = employeeService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Employee loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Authenticate the user using the service method
        String response = employeeService.authenticateAdmin(username, password);
        
        if (response.startsWith("Admin logged in")) {
            String role = "ADMIN";  // Fetch role from the database if necessary
            String token = jwtTokenUtil.generateToken(username, role);
            
            // Return the token in JSON format
            return ResponseEntity.ok().body(new JwtResponse("Bearer " + token));
        } else {
            // Return unauthorized error if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // You can create a response class to encapsulate the JWT token
    public static class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
