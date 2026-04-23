package com.valentin_prevot.invoice_app.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Date par défaut à la création

    @Column(name = "invoiced_at")
    private LocalDateTime invoicedAt;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Lien vers les lignes de facture 
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceRow> invoiceRows = new ArrayList<>();

    // Méthode pour calculer le total de la facture
    @Transient
    public Double getTotal() {
        return invoiceRows.stream()
                          .map(InvoiceRow::getAmount)
                          .reduce(0d, Double::sum);
    }
}