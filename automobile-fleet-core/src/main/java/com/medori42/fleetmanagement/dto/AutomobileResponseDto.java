package com.medori42.fleetmanagement.dto;

import com.medori42.fleetmanagement.domain.OwnerClientData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet de transfert de données pour les réponses de l'API véhicule.
 * Combine les informations du véhicule avec les données du propriétaire
 * pour fournir une vue complète.
 * 
 * <p>Utilisé comme modèle de réponse pour les endpoints REST
 * qui nécessitent des informations enrichies.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutomobileResponseDto {

    /**
     * Identifiant unique du véhicule.
     */
    private Long automobileIdentifier;

    /**
     * Marque du constructeur.
     */
    private String manufacturerName;

    /**
     * Désignation du modèle.
     */
    private String modelName;

    /**
     * Numéro de plaque d'immatriculation.
     */
    private String registrationPlate;

    /**
     * Informations du propriétaire associé.
     */
    private OwnerClientData ownerInformation;
}
