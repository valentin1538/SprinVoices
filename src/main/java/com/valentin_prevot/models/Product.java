package com.valentin_prevot.invoice_app.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;
    
    private String description;
    
    private String category;
    
    @Column(name = "unit_price")
    private Double unitPrice;
}