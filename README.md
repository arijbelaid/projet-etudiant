#  Partie 1 – API REST Spring Boot 4 (Gestion des étudiants)

## 📌 Description

Cette API REST développée avec **Spring Boot 4** permet de gérer une liste d'étudiants.
Elle expose un endpoint permettant de récupérer les étudiants depuis une base de données **PostgreSQL** exécutée via Docker.

---

## 🛠️ Technologies utilisées

* Java 17+
* Spring Boot 4
* Spring Web
* Spring Data JPA
* PostgreSQL (via Docker)
* Lombok (optionnel)
* Maven

---

## 🚀 Endpoint disponible

| Méthode | URL              | Description                     |
| ------- | ---------------- | ------------------------------- |
| GET     | `/api/etudiants` | Retourne la liste des étudiants |

---

## 🧾 Attributs d'un étudiant

* `id` : Long (auto-généré)
* `cin` : String
* `nom` : String
* `dateNaissance` : LocalDate

---

## 🐳 Lancer l'API avec Docker (PostgreSQL + Spring Boot)

Assurez-vous que Docker est installé et en cours d'exécution, puis lancez :

```bash id="sqqxrh"
docker compose up --build
```

👉 L'application sera accessible sur :
http://localhost:8080

---

## 📸 Screenshots (Partie 1)

### 1️⃣ Structure du projet

Cette capture montre l'organisation des dossiers du projet Spring Boot :

* `api-spring-boot/` : dossier principal de l'API
* `src/main/java/` : code source Java (controllers, entities, repositories)
* `src/main/resources/` : fichiers de configuration
* `pom.xml` : dépendances Maven

![Structure](screenshot/structure_projet_partie1.png)

---

### 2️⃣ Liste des étudiants (GET /api/etudiants)

Cette capture présente le résultat de l'appel API `GET /api/etudiants` avec la liste complète des étudiants.

Pour chaque étudiant :

* CIN
* Nom
* Date de naissance

![Liste des étudiants](screenshot/list_des_etudiants.png)

---

### 3️⃣ Exécution de l'API

Cette capture montre le bon fonctionnement de l'application :

* Console de lancement (logs Spring Boot)
* API accessible sur `http://localhost:8080`
* Chargement des données initiales

![Exécution](screenshot/execution.png)

---

## ✅ Résultat

L'API fonctionne correctement et permet de récupérer les données des étudiants depuis PostgreSQL via un endpoint REST simple.

# Partie 2 – Enrichissement de l'API Spring Boot

## Objectif

Cette partie enrichit le projet de la Partie 1 en ajoutant :

- Une méthode de calcul d'âge (`age()`)
- Des tests BDD avec Cucumber
- Une interface web statique (`index.html`)
- Une image Docker publiée sur Docker Hub
- Un déploiement Kubernetes (K3s)
- Une entité `Departement` avec relation `@ManyToOne`
- Une architecture en couches (DTO, Mapper, Service, Controller)
- Des opérations CRUD complètes
- Une gestion des erreurs HTTP standard
- Un cache Redis
- Un projet Jira Scrum

---

## Q3 – Tests BDD avec Cucumber

Exécution des tests Cucumber validant la méthode `age()`.

![Tests Cucumber](screenshots_partie2/cucumber_tests.png)

---

## Q4 – Page `index.html` avec Fetch JavaScript

Page statique consommant l'API et affichant les étudiants.

![Page index.html sur Kubernetes](screenshots_partie2/index_html_k8s_port_forward.png)

---

## Q6 – Déploiement sur Kubernetes (K3s)

Vérification des pods et services sur le cluster K3s.

![Pods et services Kubernetes](screenshots_partie2/k8s_pods_and_services.png)

---

## Q7 – Entité Département

Endpoint `GET /api/departements` retournant les 3 départements.

![Liste des départements](screenshots_partie2/api_departements_response.png)

---

## Q9 – Requête personnalisée

Recherche des étudiants par année d'inscription.

![Recherche par année 2022](screenshots_partie2/api_search_by_annee_2022.png)

---

## Q10 – CRUD complet

### Création d'un département

![POST département](screenshots_partie2/crud_post_departement_created.png)

### Création d'un étudiant

![GET étudiant après création](screenshots_partie2/crud_get_etudiant_created.png)

### Modification d'un étudiant

![PUT étudiant](screenshots_partie2/modify_etudiant.png)

### Suppression d'un étudiant

![DELETE étudiant](screenshots_partie2/delete_etudiant_command.png)

---

## Q11 – Gestion des erreurs

Erreur 404 retournée pour un étudiant inexistant.

![Erreur 404](screenshots_partie2/api_error_404_etudiant_not_found.png)

---

## Q14 – Organisation Jira Scrum

### Sprint 1 – API REST de base (Partie 1)

![Sprint 1 Jira](screenshots_partie2/jira_sprint1_partie1.png)

### Sprint 2 – Enrichissement (Partie 2)

![Sprint 2 Jira](screenshots_partie2/jira_sprint2_partie1.png)
![Sprint 2 Jira](screenshots_partie2/jira_sprint2_partie1.png)

---

## Conclusion

Toutes les fonctionnalités de la Partie 2 ont été implémentées et testées avec succès :

- ✅ API Spring Boot enrichie
- ✅ Tests BDD passants
- ✅ Interface web statique
- ✅ Image Docker publiée
- ✅ Déploiement Kubernetes
- ✅ CRUD complet
- ✅ Gestion des erreurs
- ✅ Organisation Agile avec Jira

# 🚀 Partie 3 – Architecture Microservices

## 📌 Description

Cette partie fait évoluer l’application vers une **architecture microservices complète**.

Le système est désormais composé de plusieurs services indépendants qui communiquent entre eux via :

- 🔍 **Eureka** (Service Discovery)
- 🔗 **OpenFeign** (communication inter-services)

Une **API Gateway** centralise tous les appels clients, tandis qu’un nouveau microservice de gestion des notes est introduit.

L’écosystème est complété par :

- 🌐 Un **frontend Next.js**
- 📱 Une **application mobile Flutter enrichie**

L’ensemble est orchestré avec **Docker Compose** pour un déploiement simple et rapide.

---

## 🛠️ Technologies utilisées

- Java 17+
- Spring Boot
- Spring Cloud (Eureka, OpenFeign, Gateway)
- PostgreSQL
- Redis
- Docker & Docker Compose
- Next.js (React)
- Flutter
- Tailwind CSS
- Swagger / OpenAPI
- Jira (Scrum)

---

## 🧱 Architecture globale

| Service             | Port | Rôle                         |
|--------------------|------|------------------------------|
| eureka-server      | 8761 | Découverte de services       |
| etudiant-service   | 8081 | Gestion des étudiants        |
| grading-service    | 8082 | Gestion des notes            |
| api-gateway        | 8080 | Point d’entrée unique        |
| frontend (Next.js) | 3000 | Interface web                |
| postgres           | 5432 | Base de données              |
| redis              | 6379 | Cache                        |

---

## 🚀 Fonctionnalités principales

### 🔍 Service Discovery (Eureka)

Tous les microservices s’enregistrent automatiquement auprès du serveur Eureka.

---

### 🔗 Communication inter-services (Feign)

Le `grading-service` communique avec `etudiant-service` afin de vérifier l’existence d’un étudiant avant l’ajout d’une note.

---

### 🚪 API Gateway

Tous les clients (web, mobile) passent uniquement par :
http://localhost:8080

---

### 📝 Microservice des notes (grading-service)

- CRUD complet des notes
- Validation des données
- Documentation Swagger intégrée

---

### 🌐 Frontend Next.js

Interface moderne permettant :

- Gestion des étudiants
- Gestion des départements

---

### 📱 Application mobile Flutter

Fonctionnalités ajoutées :

- Récupération des départements
- Sélection via `DropdownButton`
- Filtrage des étudiants par département

---

## 🧾 API – Microservice des notes

| Méthode | Endpoint           | Description            |
|--------|--------------------|------------------------|
| GET    | `/api/notes`       | Liste des notes        |
| GET    | `/api/notes/{id}`  | Détail d’une note      |
| POST   | `/api/notes`       | Ajouter une note       |
| PUT    | `/api/notes/{id}`  | Modifier une note      |
| DELETE | `/api/notes/{id}`  | Supprimer une note     |

---

## 🧩 Modèle de données – Note

- `id` : Long (auto-généré)
- `studentId` : Long
- `matiere` : String
- `valeur` : Double (0–20)

---

## 🔗 Exemple Feign Client

```java
@FeignClient(name = "etudiant-service")
public interface EtudiantClient {

    @GetMapping("/api/etudiants/{id}")
    EtudiantDTO getEtudiantById(@PathVariable Long id);
}

---
## 🚪Configuration API Gateway

---

spring:
  cloud:
    gateway:
      routes:
        - id: etudiant-service
          uri: lb://etudiant-service
          predicates:
            - Path=/api/etudiants/**,/api/departements/**

        - id: grading-service
          uri: lb://grading-service
          predicates:
            - Path=/api/notes/**
---
🌐 Frontend Next.js
📄 Pages disponibles
/etudiants → CRUD des étudiants
/etudiants/[id] → Détail / modification
/departements → Gestion des départements

---
📱 Application mobile (Flutter)

Fonctionnalités :

📥 Récupération des départements
🎯 Filtrage des étudiants
🔽 Interface avec DropdownButton



