package com.medori42.fleetmanagement.application;

import com.medori42.fleetmanagement.domain.AutomobileEntity;
import com.medori42.fleetmanagement.domain.OwnerClientData;
import com.medori42.fleetmanagement.dto.AutomobileResponseDto;
import com.medori42.fleetmanagement.persistence.AutomobileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service métier pour la gestion des opérations sur les véhicules.
 * Orchestre les appels vers la couche de persistance et la communication
 * avec le service Client Management.
 * 
 * <p>Ce service enrichit les données des véhicules avec les informations
 * des propriétaires récupérées via REST.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 * @see AutomobileEntity
 * @see AutomobileDataRepository
 */
@Service
public class FleetBusinessService {

    /**
     * URL de base du service Client Management.
     */
    private static final String CLIENT_SERVICE_BASE_URL = "http://localhost:8888/CLIENT-MANAGEMENT-SERVICE";

    /**
     * Repository pour l'accès aux données des véhicules.
     */
    private final AutomobileDataRepository automobileRepository;

    /**
     * Template REST pour la communication inter-services.
     */
    private final RestTemplate httpRestTemplate;

    /**
     * Constructeur avec injection de dépendances.
     * 
     * @param automobileRepository le repository d'accès aux données véhicules
     * @param httpRestTemplate le template REST pour les appels HTTP
     */
    @Autowired
    public FleetBusinessService(AutomobileDataRepository automobileRepository, RestTemplate httpRestTemplate) {
        this.automobileRepository = automobileRepository;
        this.httpRestTemplate = httpRestTemplate;
    }

    /**
     * Récupère tous les véhicules avec leurs informations propriétaires.
     * Effectue un appel au service Client Management pour enrichir les données.
     * 
     * @return une liste de DTOs contenant véhicules et propriétaires
     */
    public List<AutomobileResponseDto> retrieveAllAutomobiles() {
        List<AutomobileEntity> automobileList = automobileRepository.findAll();
        ResponseEntity<OwnerClientData[]> clientServiceResponse = httpRestTemplate.getForEntity(
                CLIENT_SERVICE_BASE_URL + "/api/client",
                OwnerClientData[].class);
        OwnerClientData[] ownerDataArray = clientServiceResponse.getBody();
        return automobileList.stream()
                .map(automobile -> convertToResponseDto(automobile, ownerDataArray))
                .toList();
    }

    /**
     * Récupère un véhicule spécifique par son identifiant avec le propriétaire.
     * 
     * @param automobileId l'identifiant unique du véhicule
     * @return le DTO contenant les informations complètes
     * @throws Exception si le véhicule n'existe pas
     */
    public AutomobileResponseDto retrieveAutomobileById(Long automobileId) throws Exception {
        AutomobileEntity automobile = automobileRepository.findById(automobileId)
                .orElseThrow(() -> new Exception("Identifiant véhicule invalide: " + automobileId));

        OwnerClientData ownerData = httpRestTemplate.getForObject(
                CLIENT_SERVICE_BASE_URL + "/api/client/" + automobile.getOwnerClientId(),
                OwnerClientData.class);

        return buildAutomobileResponse(automobile, ownerData);
    }

    /**
     * Convertit une entité véhicule en DTO de réponse avec recherche du propriétaire.
     * 
     * @param automobile l'entité véhicule à convertir
     * @param ownerDataArray le tableau des données propriétaires
     * @return le DTO de réponse construit
     */
    private AutomobileResponseDto convertToResponseDto(AutomobileEntity automobile, OwnerClientData[] ownerDataArray) {
        OwnerClientData matchingOwner = Arrays.stream(ownerDataArray)
                .filter(owner -> owner.getOwnerIdentifier().equals(automobile.getOwnerClientId()))
                .findFirst()
                .orElse(null);

        return buildAutomobileResponse(automobile, matchingOwner);
    }

    /**
     * Construit un objet de réponse à partir des données véhicule et propriétaire.
     * 
     * @param automobile l'entité véhicule source
     * @param ownerData les données du propriétaire
     * @return le DTO de réponse assemblé
     */
    private AutomobileResponseDto buildAutomobileResponse(AutomobileEntity automobile, OwnerClientData ownerData) {
        return AutomobileResponseDto.builder()
                .automobileIdentifier(automobile.getAutomobileId())
                .manufacturerName(automobile.getManufacturerBrand())
                .ownerInformation(ownerData)
                .registrationPlate(automobile.getLicensePlateNumber())
                .modelName(automobile.getModelDesignation())
                .build();
    }
}
