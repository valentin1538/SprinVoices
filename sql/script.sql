-- ==========================================
-- 1. CRÉATION DES INDEX (Optimisation)
-- ==========================================

CREATE INDEX idx_product_designation ON product(designation);
CREATE INDEX idx_customer_corpname ON customer(corporate_name);
CREATE INDEX idx_invoice_designation ON invoice(designation);

-- ==========================================
-- 2. CRÉATION DU TRIGGER (Sécurité Légale)
-- ==========================================

CREATE OR REPLACE TRIGGER trg_prevent_paid_invoice_update
BEFORE UPDATE ON invoice
FOR EACH ROW
BEGIN

    IF :OLD.paid_at IS NOT NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'SECURITE METIER : Il est strictement interdit de modifier une facture déjà payée et comptabilisée.');
    END IF;
END;
/

CREATE OR REPLACE VIEW v_admin_dashboard AS
SELECT 
    1 AS id, -- Identifiant fictif obligatoire pour que Hibernate fonctionne
    (SELECT COUNT(*) FROM customer) AS total_customers,
    (SELECT COUNT(*) FROM invoice) AS total_invoices,
    
    -- Chiffre d'Affaires Encaissé (Factures payées)
    NVL((SELECT SUM(total_amount_ht) FROM invoice WHERE paid_at IS NOT NULL), 0) AS total_revenue_paid,
    
    -- Chiffre d'Affaires en Attente (Facturées mais non payées)
    NVL((SELECT SUM(total_amount_ht) FROM invoice WHERE invoiced_at IS NOT NULL AND paid_at IS NULL), 0) AS total_revenue_pending
FROM dual;

-- ==========================================
-- 3. CRÉATION DE LA VUE ADMIN
-- ==========================================

CREATE OR REPLACE VIEW v_admin_dashboard AS
SELECT 
    1 AS id,
    t_cust.total_customers,
    t_inv.total_invoices,
    t_paid.total_revenue_paid,
    t_pend.total_revenue_pending
FROM 
    (SELECT COUNT(*) AS total_customers FROM customer) t_cust
CROSS JOIN 
    (SELECT COUNT(*) AS total_invoices FROM invoice) t_inv
CROSS JOIN 
    (SELECT NVL(SUM(ir.quantity * p.unit_price), 0) AS total_revenue_paid 
     FROM invoice i 
     JOIN invoice_row ir ON i.id = ir.invoice_id 
     JOIN product p ON ir.product_id = p.id 
     WHERE i.paid_at IS NOT NULL) t_paid
CROSS JOIN 
    (SELECT NVL(SUM(ir.quantity * p.unit_price), 0) AS total_revenue_pending 
     FROM invoice i 
     JOIN invoice_row ir ON i.id = ir.invoice_id 
     JOIN product p ON ir.product_id = p.id 
     WHERE i.invoiced_at IS NOT NULL AND i.paid_at IS NULL) t_pend;