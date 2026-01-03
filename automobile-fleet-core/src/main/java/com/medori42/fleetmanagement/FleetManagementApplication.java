package com.medori42.fleetmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Application principale du microservice de gestion de flotte automobile.
 * Cette application Spring Boot gère les informations des véhicules et
 * leurs associations avec les clients via communication inter-services.
 * 
 * <p>Le service communique avec le service Client Management pour récupérer
 * les informations des propriétaires de véhicules.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 */
@SpringBootApplication
public class FleetManagementApplication {

    /**
     * Délai de connexion en millisecondes pour les appels HTTP.
     */
    private static final int HTTP_CONNECTION_TIMEOUT = 5000;
    
    /**
     * Délai de lecture en millisecondes pour les appels HTTP.
     */
    private static final int HTTP_READ_TIMEOUT = 5000;

    /**
     * Point d'entrée principal de l'application Fleet Management.
     * Initialise le contexte Spring Boot et démarre le serveur.
     * 
     * @param commandLineArgs les arguments de la ligne de commande
     */
    public static void main(String[] commandLineArgs) {
        SpringApplication.run(FleetManagementApplication.class, commandLineArgs);
    }

    /**
     * Configure et fournit un bean RestTemplate pour la communication HTTP.
     * Définit les délais de connexion et de lecture pour une communication
     * fiable entre les microservices.
     * 
     * @return une instance RestTemplate configurée avec les timeouts appropriés
     */
    @Bean
    public RestTemplate httpRestTemplate() {
        RestTemplate restTemplateInstance = new RestTemplate();
        SimpleClientHttpRequestFactory httpRequestConfiguration = new SimpleClientHttpRequestFactory();
        httpRequestConfiguration.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
        httpRequestConfiguration.setReadTimeout(HTTP_READ_TIMEOUT);
        restTemplateInstance.setRequestFactory(httpRequestConfiguration);
        return restTemplateInstance;
    }
}
