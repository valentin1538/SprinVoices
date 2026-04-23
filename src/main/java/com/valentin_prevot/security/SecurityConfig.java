package com.valentin_prevot.invoice_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() 
                .requestMatchers("/login", "/error").permitAll() // <-- LA SÉCURITÉ ANTI-BOUCLE EST ICI
                .requestMatchers("/admin/**").hasRole("ADMIN") 
                .requestMatchers("/client/**").hasRole("CLIENT") 
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true) 
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    // hacher les mots de passe des utilisateurs dans la base
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}