package com.valentin_prevot.invoice_app.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "invoice_row")
public class InvoiceRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    private Double quantity;

    @Transient
    public Double getAmount() {
        if (product != null && product.getUnitPrice() != null && quantity != null) {
            return product.getUnitPrice() * quantity;
        }
        return 0d;
    }
}