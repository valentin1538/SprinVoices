# Document de Cadrage du Projet (DCP)
## Application de Facturation : SprinVoices

---

### 1. Contexte du projet
L'entreprise **SprinVoices**, spécialisée dans les prestations de services numériques, souhaite disposer rapidement d'un module de facturation simple et fiable pour remplacer son système actuel basé sur des fichiers Excel. Ce module doit permettre de réduire les erreurs, d'accélérer la facturation et d'améliorer le suivi des paiements. Le projet est mené dans un contexte court et contraint, avec une durée totale de 6 jours, ce qui impose une organisation rigoureuse et une priorisation stricte des tâches.

---

### 2. Objectifs du projet
* **Objectif principal :** Développer en 6 jours un module de facturation opérationnel permettant de gérer devis, factures et paiements.
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
| **Équipe projet (Chef de projet, Devs)** | Analyse, conception, développement, tests |
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