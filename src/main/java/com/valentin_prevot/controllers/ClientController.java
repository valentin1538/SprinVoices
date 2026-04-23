package com.valentin_prevot.invoice_app.controllers;

import com.valentin_prevot.invoice_app.models.Invoice;
import com.valentin_prevot.invoice_app.services.InvoiceService;
import com.valentin_prevot.invoice_app.services.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; // <-- IMPORT AJOUTÉ POUR LE PAIEMENT
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final InvoiceService invoiceService;
    private final PdfService pdfService; 

    public ClientController(InvoiceService invoiceService, PdfService pdfService) {
        this.invoiceService = invoiceService;
        this.pdfService = pdfService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String currentUsername = principal.getName();
        model.addAttribute("invoices", invoiceService.findInvoicesByUsername(currentUsername));
        
        return "client/dashboard";
    }

    @GetMapping("/factures/{id}/pdf")
    public ResponseEntity<byte[]> downloadInvoicePdf(@PathVariable("id") Long id, Principal principal) {
        Invoice invoice = invoiceService.findById(id);

        if (invoice == null || !invoice.getCustomer().getAppUser().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(403).build(); // Accès refusé
        }

        byte[] pdfBytes = pdfService.generateInvoicePdf(invoice);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Facture_" + invoice.getId() + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    // --- NOUVELLE MÉTHODE DE PAIEMENT ---
    @PostMapping("/factures/{id}/payer")
    public String payInvoice(@PathVariable("id") Long id, Principal principal) {
        Invoice invoice = invoiceService.findById(id);

        // SÉCURITÉ : On vérifie que la facture existe ET qu'elle appartient bien au client connecté
        if (invoice != null && invoice.getCustomer().getAppUser().getUsername().equals(principal.getName())) {
            invoiceService.markAsPaid(id);
        }

        // On redirige le client vers son tableau de bord
        return "redirect:/client/dashboard";
    }
}