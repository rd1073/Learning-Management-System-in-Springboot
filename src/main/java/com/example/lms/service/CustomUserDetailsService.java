package com.example.lms.service;

import com.example.lms.entity.EmployeePrimaryInformation;
import com.example.lms.repository.EmployeePrimaryInformationRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeePrimaryInformationRepository employeeRepository;

    public CustomUserDetailsService(EmployeePrimaryInformationRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeePrimaryInformation employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        /*return User.builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .roles(employee.getRole().name())  // Maps the Role
                .build();*/
        
         // Convert Employee to UserDetails (with role)
         GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + employee.getRole().name());
         return new User(employee.getUsername(), employee.getPassword(), Collections.singletonList(authority));
    }
}