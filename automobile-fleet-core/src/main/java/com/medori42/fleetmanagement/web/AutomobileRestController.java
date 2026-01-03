package com.medori42.fleetmanagement.web;

import com.medori42.fleetmanagement.dto.AutomobileResponseDto;
import com.medori42.fleetmanagement.application.FleetBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST exposant les endpoints de gestion de la flotte automobile.
 * Fournit une API RESTful pour la consultation des véhicules
 * avec leurs informations propriétaires.
 * 
 * <p>Les endpoints sont accessibles via le préfixe {@code /api/automobile}.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 * @see AutomobileResponseDto
 * @see FleetBusinessService
 */
@RestController
@RequestMapping("api/automobile")
public class AutomobileRestController {

    /**
     * Service métier pour les opérations sur la flotte.
     */
    private final FleetBusinessService fleetBusinessService;

    /**
     * Constructeur avec injection du service métier.
     * 
     * @param fleetBusinessService le service de gestion de flotte
     */
    @Autowired
    public AutomobileRestController(FleetBusinessService fleetBusinessService) {
        this.fleetBusinessService = fleetBusinessService;
    }

    /**
     * Récupère la liste complète des véhicules avec propriétaires.
     * 
     * @return une liste de DTOs automobile avec informations enrichies
     */
    @GetMapping
    public List<AutomobileResponseDto> getAllAutomobiles() {
        return fleetBusinessService.retrieveAllAutomobiles();
    }

    /**
     * Récupère un véhicule spécifique par son identifiant.
     * 
     * @param automobileIdentifier l'identifiant unique du véhicule
     * @return le DTO automobile avec informations du propriétaire
     * @throws Exception si le véhicule n'existe pas
     */
    @GetMapping("/{id}")
    public AutomobileResponseDto getAutomobileById(@PathVariable("id") Long automobileIdentifier) throws Exception {
        return fleetBusinessService.retrieveAutomobileById(automobileIdentifier);
    }
}
