package com.valentin_prevot.invoice_app.config;

import com.valentin_prevot.invoice_app.models.AppUser;
import com.valentin_prevot.invoice_app.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (appUserRepository.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // mot de passe haché
                admin.setRole("ADMIN");
                
                appUserRepository.save(admin);
                System.out.println("Compte Admin par défaut créé (admin / admin123)");
            }
        };
    }
}