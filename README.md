# Partie 1 – API REST Spring Boot 4 (Gestion des étudiants)

## Description

Cette API REST développée avec **Spring Boot 4** permet de gérer une liste d'étudiants.  
Elle expose un endpoint pour récupérer la liste des étudiants depuis une base de données **PostgreSQL** exécutée via Docker.

---

## Technologies utilisées

- Java 17+
- Spring Boot 4
- Spring Web
- Spring Data JPA
- PostgreSQL (via Docker)
- Lombok (optionnel)
- Maven

---

## Endpoint disponible

| Méthode | URL                     | Description                 |
|---------|-------------------------|-----------------------------|
| GET     | `/api/etudiants`        | Retourne la liste des étudiants |

---

## Attributs d'un étudiant

- `id` : Long (auto-généré)
- `cin` : String
- `nom` : String
- `dateNaissance` : LocalDate

---

## Lancer l'API avec Docker (PostgreSQL + Spring Boot)


```bash
docker compose up --build

---

### Screenshots (Partie 1)

Les captures d'écran ci-dessous illustrent les différentes étapes du projet.
1. Structure du projet (Partie 1)
Cette capture montre l'organisation des dossiers du projet Spring Boot :

api-spring-boot/ : dossier principal de l'API

src/main/java/ : code source Java (controllers, entities, repositories)

src/main/resources/ : fichiers de configuration

pom.xml : dépendances Maven

https://screenshot/structure_projet_partie1.png

2. Liste des étudiants (GET /api/etudiants)
Cette capture présente le résultat de l'appel API GET /api/etudiants avec la liste complète des 5 étudiants.
On y retrouve pour chaque étudiant :

CIN

Nom

Date de naissance

https://screenshot/list_des_etudiants.png

3. Exécution de l'API
Cette capture montre le bon fonctionnement de l'application Spring Boot :

Console de lancement (logs Spring Boot)

Confirmation que l'API est accessible sur http://localhost:8080

Affichage des données initiales chargées au démarrage

https://screenshot/execution.png




