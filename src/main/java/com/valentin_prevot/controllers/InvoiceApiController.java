package com.valentin_prevot.invoice_app.controllers;

import com.valentin_prevot.invoice_app.models.Invoice;
import com.valentin_prevot.invoice_app.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController // Indique que toutes les méthodes renvoient du JSON
@RequestMapping("/api/factures")
public class InvoiceApiController {

    private final InvoiceService invoiceService;

    public InvoiceApiController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Obtenir la liste de SES factures
    @GetMapping
    public ResponseEntity<List<Invoice>> getMyInvoices(Principal principal) {
        List<Invoice> myInvoices = invoiceService.findInvoicesByUsername(principal.getName());
        return ResponseEntity.ok(myInvoices);
    }

    // Obtenir le détail d'UNE facture précise
    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceDetails(@PathVariable("id") Long id, Principal principal) {
        Invoice invoice = invoiceService.findById(id);

        // La facture doit exister
        if (invoice == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Facture introuvable.");
        }

        // La facture DOIT appartenir au client actuellement connecté
        String currentUsername = principal.getName();
        String invoiceOwnerUsername = invoice.getCustomer().getAppUser().getUsername();

        if (!currentUsername.equals(invoiceOwnerUsername)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : Cette facture ne vous appartient pas.");
        }

        // on renvoie la facture complète en JSON
        return ResponseEntity.ok(invoice);
    }
}