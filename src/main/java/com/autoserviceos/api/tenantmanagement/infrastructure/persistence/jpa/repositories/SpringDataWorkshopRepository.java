package com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa.repositories;

import com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa.entities.WorkshopJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Spring Data interface abstraction providing database access routines for Workshop entities.
 */
public interface SpringDataWorkshopRepository extends JpaRepository<WorkshopJpaEntity, Long> {
    Optional<WorkshopJpaEntity> findByTenantId(String tenantId);
}