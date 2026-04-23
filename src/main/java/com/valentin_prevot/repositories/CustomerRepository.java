package com.valentin_prevot.invoice_app.repositories;

import com.valentin_prevot.invoice_app.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Recherche multi-critères
    Page<Customer> findByCorporateNameContainingIgnoreCaseOrNameContainingIgnoreCaseOrAppUser_UsernameContainingIgnoreCase(String corpName, String name, String username, Pageable pageable);
}