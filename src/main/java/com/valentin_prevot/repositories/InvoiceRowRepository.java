package com.valentin_prevot.invoice_app.repositories;

import com.valentin_prevot.invoice_app.models.InvoiceRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRowRepository extends JpaRepository<InvoiceRow, Long> {
}