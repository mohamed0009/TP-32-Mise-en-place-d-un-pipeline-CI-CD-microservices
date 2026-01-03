package com.medori42.clientmanagement;

import com.medori42.clientmanagement.domain.ClientEntity;
import com.medori42.clientmanagement.persistence.ClientDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application principale du microservice de gestion des clients.
 * Cette application Spring Boot fournit une API REST pour la gestion
 * des informations clients dans un environnement de microservices distribué.
 * 
 * <p>Ce service s'enregistre auprès du serveur Eureka pour permettre
 * la découverte de services et le routage dynamique via l'API Gateway.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 * @see ClientEntity
 * @see ClientDataRepository
 */
@SpringBootApplication
public class ClientManagementApplication {

    /**
     * Crée et configure le bean d'initialisation de données de démonstration.
     * Ce bean est exécuté au démarrage de l'application pour peupler
     * la base de données avec des enregistrements de test.
     * 
     * @param clientDataRepository le repository d'accès aux données clients
     * @return un CommandLineRunner qui initialise les données de démonstration
     */
    @Bean
    CommandLineRunner loadInitialData(ClientDataRepository clientDataRepository) {
        return commandLineArgs -> {
            clientDataRepository.save(new ClientEntity(1L, "Amine SAFI", 23.0f));
            clientDataRepository.save(new ClientEntity(2L, "Amal ALAOUI", 22.0f));
            clientDataRepository.save(new ClientEntity(3L, "Samir RAMI", 22.0f));
        };
    }

    /**
     * Point d'entrée principal de l'application Client Management.
     * Lance le contexte Spring Boot et démarre le serveur embarqué.
     * 
     * @param applicationArgs les arguments de ligne de commande
     */
    public static void main(String[] applicationArgs) {
        SpringApplication.run(ClientManagementApplication.class, applicationArgs);
    }
}
