package com.example.lms.controller;

import com.example.lms.security.JwtTokenUtil;
import com.example.lms.service.EmployeeService;
import com.example.lms.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
        
        
        // Check the response to decide on the status code
        if (response.startsWith("Bearer ")) {
            // Token generation successful
            return ResponseEntity.ok(new JwtResponse(response)); // Return 200 OK with the token
        } else {
            // Authentication failed, return 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
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

    public class ProtectedController {

        @GetMapping("/add-mentor")
        //@PreAuthorize("hasRole('ROLE_ADMIN')")
        //@PreAuthorize("hasRole('ROLE_ADMIN')")
       
        public String getProtectedData() {
            return "This is a protected endpoint accessible only by admins to add mentors";
        }
        
}
}
