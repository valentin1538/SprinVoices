package com.valentin_prevot.invoice_app.controllers;

import com.valentin_prevot.invoice_app.models.Product;
import com.valentin_prevot.invoice_app.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Controller
@RequestMapping("/admin/produits")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Affiche la liste des produits
    @GetMapping
    public String listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {
            
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        
        Page<Product> productPage = productService.searchAndPaginate(keyword, pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "admin/products/list";
    }

    // Affiche le formulaire pour créer un produit vide
    @GetMapping("/nouveau")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/products/form";
    }

    // Enregistre les données du formulaire dans la base
    @PostMapping("/sauvegarder")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/admin/produits";
    }

    // Affiche le formulaire pré-rempli pour modification
    @GetMapping("/editer/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/admin/produits";
        }
        model.addAttribute("product", product);
        return "admin/products/form";
    }

    // Supprime un produit
    @GetMapping("/supprimer/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Le produit a été supprimé avec succès.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Impossible : Ce produit est utilisé dans une ou plusieurs factures !");
        }
        return "redirect:/admin/produits";
    }
}