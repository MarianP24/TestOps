package com.hella.ictmanager.security;


import com.hella.ictmanager.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TECHNICIAN", "ROLE_OPERATOR")
                        .requestMatchers("/fixtures/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TECHNICIAN", "ROLE_OPERATOR")
                        .requestMatchers("/machines/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TECHNICIAN", "ROLE_OPERATOR")
                        .requestMatchers("/endpoints").hasAnyAuthority("ROLE_ADMIN", "ROLE_TECHNICIAN", "ROLE_OPERATOR")
                        .requestMatchers("/error", "/access-denied").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/access-denied")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService::loadUserByUsername;
    }
}