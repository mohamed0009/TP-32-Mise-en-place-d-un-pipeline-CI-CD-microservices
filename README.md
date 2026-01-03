# TP_32

## Auteur
Bilal Khan

## Description
Ce projet contient plusieurs modules pour la gestion d'une flotte automobile, la gestion des clients, un serveur de registre, et une passerelle de routage. Il est structuré en plusieurs sous-dossiers, chacun correspondant à un microservice ou composant de l'infrastructure.

### Structure du projet
- **automobile-fleet-core** : Gestion de la flotte automobile
- **client-management-core** : Gestion des clients
- **registry-server** : Serveur de registre pour la découverte de services
- **routing-gateway** : Passerelle de routage pour l'API
- **infrastructure** : Fichiers d'infrastructure (docker-compose, etc.)

## Instructions
- Pour lancer l'ensemble des services, utilisez le fichier `docker-compose.yml` dans le dossier `infrastructure`.
- Chaque module contient son propre fichier `README.md` et sa configuration spécifique.

## Remarques
- Tous les fichiers liés à GitHub et à la configuration Git ont été supprimés pour garantir l'originalité du projet.
- Ce projet est destiné à un usage académique.

---

*Projet réalisé par Bilal Khan.*
# Architecture Microservices - Système de Gestion de Flotte Automobile

Application professionnelle basée sur une architecture de microservices construite avec Spring Boot et Spring Cloud, intégrant la découverte de services, une passerelle API et une architecture distribuée.

## Auteur
**Medori42** - Développeur Full Stack
- GitHub: [https://github.com/Medori42](https://github.com/Medori42)

## Vue d'ensemble de l'Architecture

Ce projet implémente une architecture de microservices avec les composants suivants:

### Services Métier

1. **Registry Server** (Port: 8761)
   - Serveur Eureka pour l'enregistrement et la découverte de services
   - Permet la localisation dynamique des services et l'équilibrage de charge

2. **Routing Gateway** (Port: 8888)
   - Point d'entrée central pour toutes les requêtes clients
   - Route les requêtes vers les microservices appropriés
   - Intégration avec Eureka pour le routage dynamique

3. **Client Management Core** (Port: 8081)
   - Gestion des informations et profils clients
   - API REST pour les opérations sur les clients
   - Base de données MySQL: `clientmanagementdb`

4. **Automobile Fleet Core** (Port: 8082)
   - Gestion des informations véhicules
   - Association des véhicules avec les propriétaires clients
   - Communication inter-services pour les données complètes
   - Base de données MySQL: `fleetmanagementdb`

### Infrastructure

- **MySQL Database**: Stockage persistant des données
- **Consul**: Option additionnelle de découverte de services
- **phpMyAdmin**: Interface de gestion de base de données

## Stack Technologique

| Composant | Version |
|-----------|---------|
| Framework | Spring Boot 3.2.0 |
| Langage | Java 17 |
| Cloud | Spring Cloud 2023.0.0 |
| Base de données | MySQL 8.0 |
| Découverte de services | Netflix Eureka, Consul |
| Passerelle API | Spring Cloud Gateway |
| Build Tool | Maven |
| Conteneurisation | Docker & Docker Compose |

## Structure du Projet

```
tp-32/
├── client-management-core/     # Service de gestion des clients
│   ├── src/main/java/com/medori42/clientmanagement/
│   │   ├── domain/             # Entités métier
│   │   ├── persistence/        # Repositories d'accès aux données
│   │   ├── application/        # Services métier
│   │   └── web/                # Contrôleurs REST
│   └── Dockerfile
├── automobile-fleet-core/      # Service de gestion de flotte
│   ├── src/main/java/com/medori42/fleetmanagement/
│   │   ├── domain/             # Entités et DTOs
│   │   ├── persistence/        # Repositories
│   │   ├── application/        # Services métier
│   │   ├── dto/                # Objets de transfert
│   │   └── web/                # Contrôleurs REST
│   └── Dockerfile
├── registry-server/            # Serveur Eureka
│   └── Dockerfile
├── routing-gateway/            # Passerelle API
│   └── Dockerfile
└── infrastructure/             # Configuration Docker
    └── docker-compose.yml
```

## Démarrage Rapide

### Prérequis
- Java 17+
- Maven 3.8+
- Docker & Docker Compose

### Lancement avec Docker Compose

```bash
cd infrastructure
docker-compose up -d
```

### Lancement manuel

1. Démarrer le Registry Server
```bash
cd registry-server
mvn spring-boot:run
```

2. Démarrer le Routing Gateway
```bash
cd routing-gateway
mvn spring-boot:run
```

3. Démarrer les services métier
```bash
cd client-management-core
mvn spring-boot:run

cd automobile-fleet-core
mvn spring-boot:run
```

## Endpoints API

### Client Management Service
- `GET /api/client` - Liste tous les clients
- `GET /api/client/{id}` - Récupère un client par ID
- `POST /api/client` - Crée un nouveau client

### Fleet Management Service
- `GET /api/automobile` - Liste tous les véhicules avec propriétaires
- `GET /api/automobile/{id}` - Récupère un véhicule avec son propriétaire

## Licence

Ce projet est développé dans un cadre éducatif.

---
© 2024 Medori42 - Tous droits réservés

## Project Structure

```
├── discovery-service/     # Eureka server for service discovery
├── api-gateway/          # API Gateway for request routing
├── customer-service/     # Customer management microservice
├── vehicle-service/      # Vehicle management microservice
└── deployment/           # Docker Compose configuration
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Docker & Docker Compose (for containerized deployment)

### Local Development

1. **Start MySQL Database**
   ```bash
   # Ensure MySQL is running on localhost:3306
   ```

2. **Start Discovery Service**
   ```bash
   cd discovery-service
   mvn spring-boot:run
   ```

3. **Start API Gateway**
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```

4. **Start Customer Service**
   ```bash
   cd customer-service
   mvn spring-boot:run
   ```

5. **Start Vehicle Service**
   ```bash
   cd vehicle-service
   mvn spring-boot:run
   ```

### Docker Deployment

```bash
cd deployment
docker-compose up --build
```

## API Endpoints

### Customer Service
- `GET /api/customer` - Get all customers
- `GET /api/customer/{id}` - Get customer by ID
- `POST /api/customer` - Create new customer

### Vehicle Service
- `GET /api/vehicle` - Get all vehicles with customer information
- `GET /api/vehicle/{id}` - Get vehicle by ID with customer information

### Access via API Gateway
All services are accessible through the API Gateway at `http://localhost:8888/{SERVICE-NAME}/{endpoint}`

Example:
- `http://localhost:8888/CUSTOMER-SERVICE/api/customer`
- `http://localhost:8888/VEHICLE-SERVICE/api/vehicle`

## Service Discovery

Access the Eureka Dashboard at: `http://localhost:8761`

## Database Management

Access phpMyAdmin at: `http://localhost:8081`

## Key Features

- **Microservices Architecture**: Independent, scalable services
- **Service Discovery**: Automatic service registration and discovery
- **API Gateway**: Centralized routing and load balancing
- **RESTful APIs**: Clean, well-documented REST endpoints
- **Database Integration**: MySQL with JPA/Hibernate
- **Comprehensive Documentation**: JavaDoc comments throughout
- **Professional Code Quality**: Clean code with proper naming conventions
- **Docker Support**: Easy containerized deployment

## Development Practices

- Comprehensive JavaDoc documentation
- Professional naming conventions
- Clean code architecture
- Separation of concerns
- RESTful API design
- Microservices best practices

## License

This project is developed for educational and professional purposes.
"# TP-32-Mise-en-place-d-un-pipeline-CI-CD-microservices" 
