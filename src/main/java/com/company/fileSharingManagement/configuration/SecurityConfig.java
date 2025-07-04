package com.company.fileSharingManagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for now; enable it in production
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/files", "/files/download/{id}", "/files/share/{id}", "/styles/**").permitAll()
                .anyRequest().permitAll() // allow all for now; you can restrict later
            );
        return http.build();
    }
}
