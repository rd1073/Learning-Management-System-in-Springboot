package com.example.lms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    String token = jwtTokenUtil.extractTokenFromRequest(request);

    if (token != null && jwtTokenUtil.validateToken(token, jwtTokenUtil.extractUsername(token))) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtTokenUtil.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        String role = claims.get("role", String.class); // Extract the role from the token

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Ensure the role matches "ROLE_ADMIN"
            if ("ROLE_ADMIN".equalsIgnoreCase(role)) {
                // Set authentication with only the role, without GrantedAuthority
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, null); // No authorities
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Authenticated as ADMIN: " + role);
            }
        }
    }

    chain.doFilter(request, response);
}

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = jwtTokenUtil.extractTokenFromRequest(request);

        if (token != null) {
            String username = jwtTokenUtil.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtTokenUtil.validateToken(token, username)) {
                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(jwtTokenUtil.getSigningKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

                    //String role = claims.get("role", String.class);
                    String role = claims.get("role", String.class);
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                    


                    //SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                    //SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role); // Use the role as-is


                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.singletonList(authority));
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Decoded role: " + role);
                    System.out.println("Assigned authority: " + authority.getAuthority());

                }
            }
        }

        chain.doFilter(request, response);
    }*/
   /* @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
    String token = jwtTokenUtil.extractTokenFromRequest(request);
    String username = jwtTokenUtil.extractUsername(token);

    if (token != null && jwtTokenUtil.validateToken(token,username)) {
 
             
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtTokenUtil.validateToken(token, username)) {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(jwtTokenUtil.getSigningKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                // Extract the role from the token
                String role = claims.get("role", String.class);
                System.out.println("Decoded role: " + role);
                //System.out.println("Assigned authority: " + authority.getAuthority());

                // Check if the role is 'ADMIN' and assign authentication based on that
                if ("ADMIN".equalsIgnoreCase(role)) {
                    // If the user is an admin, set the authentication directly
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, null); // No authorities needed
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Admin user authenticated: " + username);
                }
            }
        }
    }

    chain.doFilter(request, response);
}*/

}
