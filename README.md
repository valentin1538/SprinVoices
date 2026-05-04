# SprinVoices - Module de Facturation ESN

**SprinVoices** est une application web de gestion de facturation B2B développée en 6 jours. Elle est spécifiquement conçue pour les Entreprises de Services du Numérique (ESN) gérant des prestations intellectuelles (Développement, Chefferie de projet, Cloud, IA).

## 🚀 Fonctionnalités clés

### Côté Administrateur (Service Gestion)
* **Catalogue de Prestations :** Gestion des profils d'expertise avec Taux Journalier Moyen (TJM).
* **Annuaire Clients :** Gestion des partenaires et de leurs accès sécurisés à l'extranet.
* **Suivi des Missions :** Création de factures basées sur la charge de travail (Jours/Hommes) et le profil intervenant.
* **Pilotage :** Tableaux de bord optimisés avec recherche multi-critères et pagination.

### Côté Client (Portail Partenaire)
* **Tableau de bord sécurisé :** Historique des factures émises, strictement cloisonné par utilisateur.
* **Paiement en ligne :** Simulation de règlement sécurisé et verrouillage comptable.
* **Export de documents :** Génération et téléchargement des factures au format PDF.

## 🛠 Architecture Technique

* **Backend :** Java 17+ avec **Spring Boot 3**.
* **Architecture :** MVC (Modèle-Vue-Contrôleur).
* **Sécurité :** **Spring Security** (Gestion stricte des rôles `ADMIN`/`CLIENT`, hachage des mots de passe en BCrypt).
* **Persistance :** **Spring Data JPA** (Hibernate) & **Oracle Database** (via Docker).
* **Frontend :** **Thymeleaf**, HTML5, CSS3 (Design System Corporate Tech).
* **Moteur PDF :** OpenPDF / iText.

## 📦 Installation et Déploiement

### Prérequis
* Java 17 ou supérieur.
* Maven 3.x.
* Docker Desktop.

### 1. Base de données Oracle (Docker)
Lancez le conteneur Oracle Free en arrière-plan :
```
docker run -d --name tomkatoracle -p 1521:1521 gvenzl/oracle-free
```
### 2. Configuration et Optimisation SQL
Une fois le conteneur lancé, connectez-vous à la base de données (via SQL*Plus ou DBeaver) pour appliquer les optimisations de performance et les règles métiers :

#### A. Indexation (Optimisation des recherches paginées) :
```SQL
CREATE INDEX idx_product_designation ON product(designation);
CREATE INDEX idx_customer_corpname ON customer(corporate_name);
CREATE INDEX idx_invoice_designation ON invoice(designation);
```

#### B. Sécurité Légale (Verrouillage des pièces comptables) :
```SQL
CREATE OR REPLACE TRIGGER trg_prevent_paid_invoice_update
BEFORE UPDATE ON invoice
FOR EACH ROW
BEGIN
    IF :OLD.paid_at IS NOT NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'SECURITE METIER : Il est strictement interdit de modifier une facture déjà payée et comptabilisée.');
    END IF;
END;
/
```

### 3. Lancement de l'application
Clonez le dépôt, installez les dépendances et lancez le serveur Spring Boot :
```
mvn clean install
mvn spring-boot:run
```

**Login admin géneré par defaut :**
*username : admin*
*pswd : admin123*

## 🛡 Focus Sécurité et Intégrité
Ce projet intègre une protection multicouche des données :

* **Couche Base de données** : Le trigger PL/SQL Oracle bloque physiquement toute altération d'une facture marquée comme payée, assurant la conformité légale.

* **Couche Applicative** : Les requêtes Spring Data JPA cloisonnent les données (Data Isolation) en forçant le filtrage par le username du client connecté, empêchant toute fuite de données inter-clients.

