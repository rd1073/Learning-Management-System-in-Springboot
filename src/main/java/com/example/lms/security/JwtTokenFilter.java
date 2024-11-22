package com.example.lms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.lms.service.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, CustomUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

                String path = request.getServletPath();

    // Skip token validation for registration and login
    if (path.equals("/api/register") || path.equals("/api/login")) {
        chain.doFilter(request, response);
        return;
    }
    
        String token = jwtTokenUtil.extractTokenFromRequest(request);
        System.out.println("Token extracted from request: " + token);
    
        if (token != null) {
            String username = jwtTokenUtil.extractUsername(token);
            System.out.println("Token is not null, validating...");
            if (username!=null && jwtTokenUtil.validateToken(token, username)) {
                String role = jwtTokenUtil.extractClaims(token).get("role", String.class);
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(authority));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Setting authentication: " + authentication);
                System.out.println("Token validated successfully");
                
                 
            } else {
                System.out.println("Token validation failed");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return; // Stop processing the request if the token is invalid
            }
        } else {
            System.out.println("No token found in request");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
            return; // Stop processing the request if no token is found
        }
    
        chain.doFilter(request, response);
    }
    
    


     
                            

}
