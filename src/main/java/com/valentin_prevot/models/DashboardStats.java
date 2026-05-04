package com.valentin_prevot.invoice_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "v_admin_dashboard")
public class DashboardStats {

    @Id
    private Long id;
    private Long totalCustomers;
    private Long totalInvoices;
    private Double totalRevenuePaid;
    private Double totalRevenuePending;

    // Getters uniquement (pas de Setters car lecture seule)
    public Long getId() { return id; }
    public Long getTotalCustomers() { return totalCustomers; }
    public Long getTotalInvoices() { return totalInvoices; }
    public Double getTotalRevenuePaid() { return totalRevenuePaid; }
    public Double getTotalRevenuePending() { return totalRevenuePending; }
}