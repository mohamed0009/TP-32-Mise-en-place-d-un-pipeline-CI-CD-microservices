package com.medori42.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

/**
 * Application principale de la passerelle API.
 * Cette passerelle Spring Cloud Gateway route les requêtes entrantes
 * vers les microservices appropriés en utilisant la découverte de services.
 * 
 * <p>Intègre avec le serveur Eureka pour le routage dynamique
 * sans configuration manuelle des routes.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 */
@SpringBootApplication
public class RoutingGatewayApplication {

    /**
     * Point d'entrée principal de l'application Gateway.
     * Démarre le contexte Spring Boot et le serveur de routage.
     * 
     * @param launchArguments les arguments de lancement de l'application
     */
    public static void main(String[] launchArguments) {
        SpringApplication.run(RoutingGatewayApplication.class, launchArguments);
    }

    /**
     * Configure la découverte dynamique des routes via Eureka.
     * Permet la création automatique des routes pour les services
     * enregistrés dans le registre de services.
     * 
     * @param reactiveClient le client réactif de découverte de services
     * @param locatorProperties les propriétés de configuration du locator
     * @return le locator configuré pour le routage dynamique
     */
    @Bean
    DiscoveryClientRouteDefinitionLocator configureRouteDiscovery(
            ReactiveDiscoveryClient reactiveClient,
            DiscoveryLocatorProperties locatorProperties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveClient, locatorProperties);
    }
}
