package com.example.lms.security;
import com.example.lms.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    

    public SecurityConfig(JwtTokenFilter jwtTokenFilter, CustomUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable() 
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// Disable CSRF since we use JWTs (stateless sessions)
         .and()
         .authorizeHttpRequests(auth -> auth
         .requestMatchers("/api/register", "/api/login", "/error", "/api/employee/register").permitAll() // Publicly accessible endpoints
         //.requestMatchers("/api/admin/**", "/api/add-mentor").hasAuthority("ROLE_ADMIN") // Restricted to ADMIN role
         .requestMatchers("/api/admin/**", "/api/mentors/**","/api/batches/**").hasAuthority("ROLE_ADMIN")
         .requestMatchers("/api/employees/**").hasAuthority("ROLE_EMPLOYEE")
         .requestMatchers("/api/attendance/mark","api/mock/create","api/mock-rating").hasAuthority("ROLE_MENTOR")

         .anyRequest().authenticated() // Secure all other endpoints
     ) 
        
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

    
    //http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);


    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
