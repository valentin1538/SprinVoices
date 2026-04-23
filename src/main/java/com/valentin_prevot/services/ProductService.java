package com.valentin_prevot.invoice_app.services;

import com.valentin_prevot.invoice_app.models.Product;
import com.valentin_prevot.invoice_app.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Injection de dépendance via le constructeur
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Récupérer tous les produits
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Récupérer un produit par son ID
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Sauvegarder (Créer ou Modifier)
    public void save(Product product) {
        productRepository.save(product);
    }

    // Supprimer un produit
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> searchAndPaginate(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return productRepository.findByDesignationContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword, pageable);
        }
        return productRepository.findAll(pageable);
    }
}