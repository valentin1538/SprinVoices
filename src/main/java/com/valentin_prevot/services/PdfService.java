package com.valentin_prevot.invoice_app.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.valentin_prevot.invoice_app.models.Invoice;
import com.valentin_prevot.invoice_app.models.InvoiceRow;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class PdfService {

    public byte[] generateInvoicePdf(Invoice invoice) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Titre
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph("FACTURE N° " + invoice.getId(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Infos Client & Facture
            document.add(new Paragraph("Client : " + invoice.getCustomer().getCorporateName()));
            document.add(new Paragraph("Désignation : " + invoice.getDesignation()));
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            document.add(new Paragraph("Date de création : " + invoice.getCreatedAt().format(dtf)));
            document.add(Chunk.NEWLINE);

            // Tableau des produits
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            
            // En-têtes du tableau
            table.addCell(new PdfPCell(new Phrase("Produit")));
            table.addCell(new PdfPCell(new Phrase("Prix Unitaire")));
            table.addCell(new PdfPCell(new Phrase("Quantité")));
            table.addCell(new PdfPCell(new Phrase("Sous-total")));

            // Remplissage des lignes
            for (InvoiceRow row : invoice.getInvoiceRows()) {
                table.addCell(row.getProduct().getDesignation());
                table.addCell(row.getProduct().getUnitPrice() + " €");
                table.addCell(String.valueOf(row.getQuantity()));
                table.addCell(row.getAmount() + " €");
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            // Total Général
            Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph total = new Paragraph("TOTAL À PAYER : " + invoice.getTotal() + " €", totalFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}