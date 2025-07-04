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
            .csrf(csrf -> csrf.disable()) // You can enable this later
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/files", "/files/download/**", "/files/share/**", "/styles/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(); // 👈 This line enables Google & GitHub OAuth2 login

        return http.build();
    }
}
