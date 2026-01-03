package com.medori42.fleetmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité JPA représentant un véhicule dans le système de gestion de flotte.
 * Cette classe modélise les informations essentielles d'un véhicule
 * et sa relation avec un propriétaire client.
 * 
 * <p>Chaque véhicule est associé à un client via l'identifiant
 * {@code ownerClientId} qui référence le service Client Management.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 */
@Entity
@Table(name = "automobiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutomobileEntity {

    /**
     * Identifiant technique unique du véhicule.
     * Généré automatiquement via stratégie d'identité.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automobile_id")
    private Long automobileId;

    /**
     * Marque du constructeur automobile.
     * Exemples: Toyota, BMW, Mercedes-Benz.
     */
    @Column(name = "manufacturer_brand", nullable = false)
    private String manufacturerBrand;

    /**
     * Désignation du modèle du véhicule.
     * Nom commercial du modèle spécifique.
     */
    @Column(name = "model_designation")
    private String modelDesignation;

    /**
     * Numéro d'immatriculation officiel du véhicule.
     * Plaque d'immatriculation ou numéro d'enregistrement.
     */
    @Column(name = "license_plate", unique = true)
    private String licensePlateNumber;

    /**
     * Identifiant du client propriétaire du véhicule.
     * Référence vers le service Client Management.
     */
    @Column(name = "owner_client_id")
    private Long ownerClientId;
}
