package com.valentin_prevot.invoice_app.repositories;

import com.valentin_prevot.invoice_app.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Spring Data JPA génère automatiquement la requête SQL grâce au nom de cette méthode !
    Page<Product> findByDesignationContainingIgnoreCaseOrCategoryContainingIgnoreCase(String designation, String category, Pageable pageable);
}