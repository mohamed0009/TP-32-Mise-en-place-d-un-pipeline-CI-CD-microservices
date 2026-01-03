package com.medori42.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Application principale du serveur de registre de services.
 * Implémente un serveur Eureka pour l'enregistrement et la découverte
 * des microservices dans l'architecture distribuée.
 * 
 * <p>Ce serveur permet aux autres services de s'enregistrer dynamiquement
 * et de découvrir les instances disponibles pour la communication.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

    /**
     * Point d'entrée principal du serveur de registre.
     * Initialise le contexte Spring Boot et démarre le serveur Eureka.
     * 
     * @param startupArguments les arguments de démarrage de l'application
     */
    public static void main(String[] startupArguments) {
        SpringApplication.run(ServiceRegistryApplication.class, startupArguments);
    }
}
