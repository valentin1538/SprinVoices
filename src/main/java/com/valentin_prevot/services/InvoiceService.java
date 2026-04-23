package com.valentin_prevot.invoice_app.services;

import com.valentin_prevot.invoice_app.models.Invoice;
import com.valentin_prevot.invoice_app.models.InvoiceRow;
import com.valentin_prevot.invoice_app.repositories.InvoiceRepository;
import com.valentin_prevot.invoice_app.repositories.InvoiceRowRepository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceRowRepository invoiceRowRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceRowRepository invoiceRowRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceRowRepository = invoiceRowRepository;
    }

    // --- CLIENT ---
    public List<Invoice> findInvoicesByUsername(String username) {
        return invoiceRepository.findByCustomer_AppUser_Username(username);
    }

    // --- ADMIN ---
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    // en-tête facture
    public Invoice createInvoice(Invoice invoice) {
        invoice.setCreatedAt(LocalDateTime.now());
        return invoiceRepository.save(invoice);
    }

    // Ajouter une ligne à la facture
    public void addRowToInvoice(Long invoiceId, InvoiceRow row) {
        Invoice invoice = findById(invoiceId);
        if (invoice != null) {
            row.setInvoice(invoice);
            invoiceRowRepository.save(row);
        }
    }

    // GESTION DES ÉTATS
    public void markAsInvoiced(Long invoiceId) {
        Invoice invoice = findById(invoiceId);
        if (invoice != null && invoice.getInvoicedAt() == null) {
            invoice.setInvoicedAt(LocalDateTime.now());
            invoiceRepository.save(invoice);
        }
    }

    public void markAsPaid(Long invoiceId) {
        Invoice invoice = findById(invoiceId);
        if (invoice != null && invoice.getPaidAt() == null) {
            invoice.setPaidAt(LocalDateTime.now());
            invoiceRepository.save(invoice);
        }
    }
    
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public Page<Invoice> searchAndPaginate(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return invoiceRepository.findByDesignationContainingIgnoreCaseOrCustomer_CorporateNameContainingIgnoreCase(keyword, keyword, pageable);
        }
        return invoiceRepository.findAll(pageable);
    }
}