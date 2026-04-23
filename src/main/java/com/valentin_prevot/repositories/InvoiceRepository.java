package com.valentin_prevot.invoice_app.repositories;

import com.valentin_prevot.invoice_app.models.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // N'oubliez pas cet import pour les Listes !

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    // 1. ANCIENNE MÉTHODE RESTAURÉE : Utilisée par le ClientController pour voir ses propres factures
    List<Invoice> findByCustomer_AppUser_Username(String username);

    // 2. NOUVELLE MÉTHODE : Utilisée par l'Admin pour la recherche et la pagination
    Page<Invoice> findByDesignationContainingIgnoreCaseOrCustomer_CorporateNameContainingIgnoreCase(String designation, String corpName, Pageable pageable);
}