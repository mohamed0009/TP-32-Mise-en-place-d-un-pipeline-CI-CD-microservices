package com.medori42.clientmanagement.domain;

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
 * Entité JPA représentant un client dans le système.
 * Cette classe définit le modèle de données pour le stockage
 * des informations relatives aux clients.
 * 
 * <p>Les instances de cette classe sont persistées dans la table
 * {@code clients} de la base de données MySQL.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 */
@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    /**
     * Identifiant technique unique du client.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientIdentifier;

    /**
     * Nom complet du client.
     * Comprend le prénom et le nom de famille.
     */
    @Column(name = "complete_name", nullable = false)
    private String completeName;

    /**
     * Âge du client en années.
     * Valeur décimale pour permettre une précision accrue.
     */
    @Column(name = "client_age")
    private Float clientAge;
}
