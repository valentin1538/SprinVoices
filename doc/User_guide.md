# 📘 Guide Utilisateur - Application SprinVoices

Bienvenue dans le guide utilisateur de **SprinVoices**, votre plateforme intégrée de gestion de facturation pour prestations de services numériques. Ce document vous accompagnera dans la prise en main de l'outil, que vous soyez gestionnaire (Admin) ou partenaire (Client).

---

## 🔐 Accès à l'application

L'accès à l'interface est sécurisé. Selon votre profil, vous serez redirigé vers votre espace dédié après connexion :
- **Administrateurs :** Gestion complète du catalogue, des clients et de la facturation globale.
- **Clients :** Consultation de l'historique personnel, téléchargement et paiement des factures.

---

## 🛠 Espace Administration (Gestionnaire)

### 1. Gestion du Catalogue de Prestations
Le catalogue centralise toutes les expertises de l'agence.
- **Visualisation :** Accédez à la liste complète des prestations. Utilisez la barre de recherche pour filtrer par intitulé ou pôle d'expertise.
- **Ajout :** Cliquez sur **"+ Ajouter une prestation"**. Renseignez la désignation et le **TJM** (Taux Journalier Moyen).
- **Pagination :** La liste est paginée par blocs de 5 éléments pour une navigation fluide.

### 2. Gestion de l'Annuaire Clients
- **Consultation :** Recherchez un partenaire par sa raison sociale ou son identifiant via le filtre de recherche.
- **Ajout :** Créez une fiche client pour lui ouvrir un accès à l'extranet.

### 3. Cycle de Facturation (Suivi des Missions)
C'est le cœur de l'application. Une facture suit trois étapes :
1. **Brouillon :** Lors de l'initialisation de la mission.
2. **Facturée :** Une fois la mission validée et émise au client.
3. **Payée :** Une fois le règlement enregistré.

**Création d'une facture :**
1. Allez dans **"Suivi des Missions"** > **"+ Initialiser une mission"**.
2. Sélectionnez le client et nommez le projet.
3. **Ajout de lignes :** Pour chaque intervenant, sélectionnez la prestation et saisissez la **charge de travail (en jours)**. Le montant total est calculé automatiquement.

---

## 👤 Espace Partenaire (Client)

### 1. Tableau de Bord Personnel
Dès votre connexion, vous visualisez l'ensemble des factures émises par SprinVoices pour votre entreprise.
- Les factures sont triées par état (En attente, Payée).
- Vous ne voyez **que** vos propres documents (sécurité et confidentialité garanties).

### 2. Paiement des Factures
Pour régulariser une facture :
1. Cliquez sur le bouton **"💳 Payer"** à côté de la facture concernée.
2. Le statut passera instantanément à **"PAYÉE"**.
3. **Important :** Une fois payée, la facture est verrouillée en base de données. Elle ne peut plus être modifiée par l'administration, garantissant l'intégrité de votre comptabilité.

### 3. Téléchargement PDF
À tout moment, vous pouvez cliquer sur l'icône **"📄 PDF"** pour générer et télécharger votre facture officielle au format professionnel, incluant le détail des prestations et les mentions légales.

---

## 🔍 Fonctions Avancées

### Recherche et Filtrage
Toutes les tables de l'application (Prestations, Clients, Factures) disposent d'un champ de recherche "Live". Vous pouvez filtrer par :
- Nom de projet ou de client.
- Type d'expertise ou catégorie.
- Mots-clés spécifiques.

### Intégrité des données
L'application intègre une sécurité de niveau industriel :
- **Indexation :** Les recherches sont instantanées grâce à l'optimisation Oracle.
- **Verrouillage comptable :** Un déclencheur (Trigger) empêche toute altération accidentelle des documents payés, conformément aux normes de facturation en vigueur.

---

*© 2026 SprinVoices - Module de Facturation*