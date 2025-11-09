package com.ExploreEase.ExploreEase.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // ✅ Define the Security Filter Chain correctly
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF and sessions for APIs
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public pages
                .requestMatchers("/", "/home", "/login", "/register", "/tours", "/add-tour",
                                 "/tour-details/**", "/book-tour/**", "/booking-success",
                                 "/images/**", "/css/**","/manage-bookings", "/js/**").permitAll()

                // Public APIs
                .requestMatchers("/api/auth/**", "/api/tours/**").permitAll()

                // Booking APIs
                .requestMatchers("/api/bookings/**").permitAll()  // or authenticated() if JWT required

                // Admin APIs
                .requestMatchers("/api/admin/**","/api/bookings/cancel/**").hasRole("ADMIN")

                // Everything else
                .anyRequest().permitAll()
            )

            // Disable form login (JWT handles auth)
            .formLogin(form -> form.disable())

            // Disable HTTP Basic
            .httpBasic(basic -> basic.disable());

        return http.build();
    }



    // ✅ Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ AuthenticationManager bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
