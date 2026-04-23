package com.valentin_prevot.invoice_app.services;

import com.valentin_prevot.invoice_app.models.Customer;
import com.valentin_prevot.invoice_app.repositories.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // La méthode de sauvegarde avec la logique de sécurité intégrée
    public void save(Customer customer) {
        // Si NOUVEAU client = ID utilisateur null
        if (customer.getAppUser() != null && customer.getAppUser().getId() == null) {
            // rôle CLIENT
            customer.getAppUser().setRole("CLIENT");
            
            // hache mot de passe
            String plainPassword = customer.getAppUser().getPassword();
            customer.getAppUser().setPassword(passwordEncoder.encode(plainPassword));
        }
        customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Page<Customer> searchAndPaginate(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return customerRepository.findByCorporateNameContainingIgnoreCaseOrNameContainingIgnoreCaseOrAppUser_UsernameContainingIgnoreCase(keyword, keyword, keyword, pageable);
        }
        return customerRepository.findAll(pageable);
    }
}