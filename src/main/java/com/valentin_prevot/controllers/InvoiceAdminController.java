package com.valentin_prevot.invoice_app.controllers;

import com.valentin_prevot.invoice_app.models.Invoice;
import com.valentin_prevot.invoice_app.models.InvoiceRow;
import com.valentin_prevot.invoice_app.services.CustomerService;
import com.valentin_prevot.invoice_app.services.InvoiceService;
import com.valentin_prevot.invoice_app.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin/factures")
public class InvoiceAdminController {

    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final ProductService productService;

    public InvoiceAdminController(InvoiceService invoiceService, CustomerService customerService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.productService = productService;
    }

    // Liste de toutes les factures
    @GetMapping
    public String listInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {
            
        int pageSize = 10; // Pour les factures, on en met 10 par page c'est mieux
        Pageable pageable = PageRequest.of(page, pageSize);
        
        Page<Invoice> invoicePage = invoiceService.searchAndPaginate(keyword, pageable);

        model.addAttribute("invoices", invoicePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", invoicePage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "admin/invoices/list";
    }

    // Formulaire pour NOUVELLE facture
    @GetMapping("/nouveau")
    public String showAddForm(Model model) {
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("customers", customerService.findAll());
        return "admin/invoices/create";
    }

    // Sauvegarde de la nouvelle facture et redirection vers SES DÉTAILS
    @PostMapping("/sauvegarder")
    public String saveInvoice(@ModelAttribute("invoice") Invoice invoice) {
        Invoice savedInvoice = invoiceService.createInvoice(invoice);
        // Redirection
        return "redirect:/admin/factures/" + savedInvoice.getId();
    }

    // Supprimer une facture entière
    @GetMapping("/supprimer/{id}")
    public String deleteInvoice(@PathVariable("id") Long id) {
        invoiceService.deleteById(id);
        return "redirect:/admin/factures";
    }

    // Afficher les détails d'une facture
    @GetMapping("/{id}")
    public String showInvoiceDetails(@PathVariable("id") Long id, Model model) {
        Invoice invoice = invoiceService.findById(id);
        if (invoice == null) {
            return "redirect:/admin/factures";
        }
        model.addAttribute("invoice", invoice);
        model.addAttribute("newRow", new InvoiceRow());
        model.addAttribute("products", productService.findAll());
        return "admin/invoices/details";
    }

    // Ajouter une ligne (produit + quantité) à la facture
    @PostMapping("/{invoiceId}/lignes") 
    public String addRow(@PathVariable("invoiceId") Long invoiceId, @ModelAttribute("newRow") InvoiceRow row) {
        
        row.setId(null);
        
        invoiceService.addRowToInvoice(invoiceId, row);
        return "redirect:/admin/factures/" + invoiceId;
    }

    // Passer la facture à l'état "FACTURÉE"
    @PostMapping("/{id}/facturer")
    public String markAsInvoiced(@PathVariable("id") Long id) {
        invoiceService.markAsInvoiced(id);
        return "redirect:/admin/factures/" + id;
    }

    // Passer la facture à l'état "PAYÉE"
    @PostMapping("/{id}/payer")
    public String markAsPaid(@PathVariable("id") Long id) {
        invoiceService.markAsPaid(id);
        return "redirect:/admin/factures/" + id;
    }
}