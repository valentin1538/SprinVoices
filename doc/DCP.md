# Document de Cadrage du Projet (DCP)
## Application de Facturation : SprinVoices

---

### 1. Contexte du projet
Notre entreprise, spécialisée dans les prestations de services informatiques, souhaite concevoir et intégrer son propre module de facturation interne baptisé **SprinVoices**. Cet outil sur-mesure a pour but de remplacer notre système de gestion actuel basé sur des fichiers Excel, afin de réduire les erreurs de saisie, d'accélérer l'émission des factures et d'optimiser le suivi des paiements. Ce projet de développement technologique, reposant sur une architecture **Java et Oracle**, est mené dans un contexte particulièrement court et contraint avec une durée totale fixée à 6 jours. Ce délai restreint impose à l'équipe de développement une organisation rigoureuse et une priorisation stricte des fonctionnalités à livrer pour ce premier MVP.

---

### 2. Objectifs du projet
* **Objectif principal :** Développer en 6 jours un module de facturation opérationnel permettant de gérer factures et paiements liés à des prestations.
* **Objectifs secondaires :**
    * Assurer une interface simple et intuitive.
    * Garantir la cohérence et la sécurité des données via Oracle.
    * Permettre l'export PDF des documents.
* **Critères de réussite :**
    * Le module couvre toutes les fonctionnalités du périmètre défini.
    * Le système est stable et utilisable par un utilisateur non technique.
    * Le projet est livré dans le délai imparti (6 jours de développement).

---

### 3. Périmètre du projet

#### 3.1. Périmètre fonctionnel - Inclus
* Gestion des clients (Raison sociale, identifiants extranet).
* Gestion d'un catalogue de prestations IT (Jours/Hommes, TJM).
* Création et suivi des devis / missions.
* Génération automatique des factures.
* Suivi des paiements et simulation de règlement.
* Export PDF des factures pour les clients.
* Tableaux de bord (Admin et Client) avec pagination et filtres de recherche.

#### 3.2. Périmètre - Exclusions
* Comptabilité avancée (bilans, rapprochement bancaire).
* Relances automatiques par email.
* Intégration avec d'autres systèmes (ERP tiers).

---

### 4. Acteurs et parties prenantes k\]vk

| Acteur | Rôle |
| :--- | :--- |
| **Direction SprinVoices** | Commanditaire |
| **Service administratif / Clients finaux** | Utilisateurs finaux |
| **Développeur Full-Stack (Solo)** | Chef de projet, analyse, conception, développement |
| **Intelligence Artificielle (Gemini)** | Support à l'architecture, aide à la génération de code et optimisation des scripts SQL |
| **Référent pédagogique** | Validation et suivi |

---

### 5. Contraintes du projet
* **Contraintes majeures :** Durée totale de 6 jours, non extensible. Travail en équipe simulé. Priorisation stricte des fonctionnalités essentielles (MVP).
* **Contraintes techniques :** Développement en **Java** (Spring Boot) , Base de données **Oracle** (via Docker) , Architecture logicielle **MVC** recommandée.

---

### 6. Risques identifiés 

| Risque | Impact | Prévention |
| :--- | :--- | :--- |
| Retard dans la conception | Élevé | Validation rapide du DCP et découpage WBS immédiat. |
| Difficultés techniques (Docker, Oracle, Spring Security) | Élevé | Limiter les outils complexes et conserver une architecture MVC éprouvée. |

---

### 7. Livrables attendus
* Document de cadrage (DCP).
* WBS, OBS, RBS.
* Planning prévisionnel (GANTT + chemin critique).
* Application Java fonctionnelle (Code source + scripts SQL Oracle).
* Documentation utilisateur.

---

### 8. Organisation générale du projet
Le projet adoptera une approche itérative rapide adaptée à la contrainte de 6 jours. Le travail est découpé en trois phases :
1.  **Conception & Architecture** (Jour 1-2).
2.  **Développement Backend/Frontend** (Jour 3-4).
3.  **Finalisation & Sécurité** (Jour 5-6).

---

### 9. Validation du cadrage
Ce document fige le périmètre de la première version (MVP) de l'application **SprinVoices**. Toute modification ultérieure devra faire l'objet d'une nouvelle évaluation de délai.