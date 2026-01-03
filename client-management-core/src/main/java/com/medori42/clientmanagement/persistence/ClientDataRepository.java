package com.medori42.clientmanagement.persistence;

import com.medori42.clientmanagement.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'accès aux données des clients.
 * Hérite des méthodes CRUD standard de JpaRepository et peut être
 * étendue avec des requêtes personnalisées.
 * 
 * <p>Cette interface utilise Spring Data JPA pour générer
 * automatiquement les implémentations des méthodes d'accès aux données.</p>
 * 
 * @author Medori42
 * @version 2.0.0
 * @since 2024-12
 * @see ClientEntity
 * @see JpaRepository
 */
@Repository
public interface ClientDataRepository extends JpaRepository<ClientEntity, Long> {
    
    // Les méthodes CRUD sont automatiquement fournies par JpaRepository
    // Des méthodes de requête personnalisées peuvent être ajoutées ici
}
