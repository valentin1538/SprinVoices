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