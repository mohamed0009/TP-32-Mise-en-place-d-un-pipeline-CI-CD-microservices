package com.medori42.clientmanagement.application;

import com.medori42.clientmanagement.domain.ClientEntity;
import com.medori42.clientmanagement.persistence.ClientDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service métier pour la gestion des opérations clients.
 * Implémente la logique métier et orchestre les appels
 * vers la couche de persistance.
 * 
 * <p>Ce service est injecté dans les contrôleurs REST pour
 * traiter les requêtes relatives aux clients.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 * @see ClientEntity
 * @see ClientDataRepository
 */
@Service
public class ClientBusinessService {

    /**
     * Repository pour l'accès aux données clients.
     */
    private final ClientDataRepository clientRepository;

    /**
     * Constructeur avec injection de dépendances.
     * 
     * @param clientRepository le repository d'accès aux données
     */
    @Autowired
    public ClientBusinessService(ClientDataRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Récupère l'ensemble des clients enregistrés.
     * 
     * @return une liste contenant tous les clients
     */
    public List<ClientEntity> retrieveAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Recherche un client spécifique par son identifiant.
     * 
     * @param clientId l'identifiant unique du client recherché
     * @return l'entité client correspondante
     * @throws Exception si l'identifiant ne correspond à aucun client
     */
    public ClientEntity retrieveClientById(Long clientId) throws Exception {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new Exception("Identifiant client invalide: " + clientId));
    }

    /**
     * Enregistre un nouveau client dans la base de données.
     * 
     * @param newClient l'entité client à persister
     */
    public void registerNewClient(ClientEntity newClient) {
        clientRepository.save(newClient);
    }
}
