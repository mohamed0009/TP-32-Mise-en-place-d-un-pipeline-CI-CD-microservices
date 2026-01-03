package com.medori42.clientmanagement.web;

import com.medori42.clientmanagement.domain.ClientEntity;
import com.medori42.clientmanagement.application.ClientBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST exposant les endpoints de gestion des clients.
 * Fournit une API RESTful pour les opérations CRUD sur les clients.
 * 
 * <p>Les endpoints sont accessibles via le préfixe {@code /api/client}.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 * @see ClientEntity
 * @see ClientBusinessService
 */
@RestController
@RequestMapping("api/client")
public class ClientRestController {

    /**
     * Service métier pour les opérations clients.
     */
    private final ClientBusinessService clientBusinessService;

    /**
     * Constructeur avec injection du service métier.
     * 
     * @param clientBusinessService le service de gestion des clients
     */
    @Autowired
    public ClientRestController(ClientBusinessService clientBusinessService) {
        this.clientBusinessService = clientBusinessService;
    }

    /**
     * Récupère la liste complète des clients.
     * 
     * @return une liste de toutes les entités clients
     */
    @GetMapping
    public List<ClientEntity> getAllClients() {
        return clientBusinessService.retrieveAllClients();
    }

    /**
     * Récupère un client spécifique par son identifiant.
     * 
     * @param clientIdentifier l'identifiant unique du client
     * @return l'entité client correspondante
     * @throws Exception si le client n'existe pas
     */
    @GetMapping("/{id}")
    public ClientEntity getClientById(@PathVariable("id") Long clientIdentifier) throws Exception {
        return clientBusinessService.retrieveClientById(clientIdentifier);
    }

    /**
     * Crée un nouveau client dans le système.
     * 
     * @param clientData les données du client à créer
     * @return une réponse HTTP avec le statut de création
     */
    @PostMapping
    public ResponseEntity<Void> createNewClient(@RequestBody ClientEntity clientData) {
        clientBusinessService.registerNewClient(clientData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
