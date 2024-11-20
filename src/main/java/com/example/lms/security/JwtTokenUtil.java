/*package com.example.lms.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtTokenUtil {

     
    private static final String SECRET_KEY = "your_super_secure_512_bit_secret_key_here_that_is_64_characters!";
 // Use a more secure key in production
    private static final long EXPIRATION_TIME = 86400000;
    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // 24 hours

    public String generateToken(String username, String role) {


        return Jwts.builder()
                .setSubject(username)
                 
                .claim("role", "ROLE_" + role)  // Ensure role is prefixed with 'ROLE_'
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey) 
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}*/

package com.example.lms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;

@Component
public class JwtTokenUtil {

    // Ensure this is the same key used for signing and parsing
    private static final String SECRET_KEY = "your_super_secure_512_bit_secret_key_here_that_is_64_characters!";  // Make sure it's the same
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    // Method to get the signing key from the SECRET_KEY string
    
    public SecretKey getSigningKey() {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        // Add a log to check the key used
        System.out.println("Signing Key: " + key);
        return key;
    }
    

     

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)  // Use role as-is (assumes it already includes "ROLE_")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)  // Sign with the same key
                .compact();
    }
    

    // Extract the username from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Use the same key for parsing
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Extract expiration date from token
    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Use the same key for parsing
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate token by username and expiration check
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
     

    
}

