package com.example.lms.security;
import com.example.lms.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);


    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, CustomUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    /*protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = extractTokenFromHeader(request);
        
        // Check if the token is present and valid
        if (token != null) {
            String username = jwtTokenUtil.extractUsername(token);

            // Validate the token and check if it matches the username
            if (jwtTokenUtil.validateToken(token, username)) {
                logger.info("Valid token for user: {}", username);
                var userDetails = userDetailsService.loadUserByUsername(username);
                
                // Set authentication in the context
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.warn("Invalid or expired token for user: {}", username);
            }*/
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
    String token = extractTokenFromHeader(request);

    if (token != null) {
        try {
            String username = jwtTokenUtil.extractUsername(token);

            /*if (username != null && jwtTokenUtil.validateToken(token,username)) {
                var userDetails = userDetailsService.loadUserByUsername(username);

                // Create authentication object
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Authenticated user: {}", username);
            }*/

            if (username != null && jwtTokenUtil.validateToken(token, username)) {
                var userDetails = userDetailsService.loadUserByUsername(username);
            
                // Create authentication object
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
            
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Authenticated user: {}", username);
            }
            
        } catch (Exception e) {
            logger.error("Could not set user authentication in security context", e);
        }
    
        }

        // Continue the filter chain
        chain.doFilter(request, response);
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Extract token from "Bearer <token>"
        }
        return null;

    }
}
