package com.medori42.fleetmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet de transfert de données représentant un propriétaire client.
 * Utilisé pour recevoir les données du service Client Management
 * via l'API REST.
 * 
 * <p>Cette classe n'est pas une entité JPA car les données client
 * sont gérées par un microservice séparé.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerClientData {

    /**
     * Identifiant unique du propriétaire client.
     */
    private Long ownerIdentifier;

    /**
     * Nom complet du propriétaire.
     */
    private String ownerFullName;

    /**
     * Âge du propriétaire en années.
     */
    private Integer ownerAge;
}
