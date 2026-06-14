package com.autoserviceos.api.tenantmanagement.domain.model.repositories;

import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;
import java.util.Optional;

/**
 * Domain repository port defining the persistence contract for Workshop aggregates.
 */
public interface WorkshopRepository {
    Workshop save(Workshop workshop);
    Optional<Workshop> findById(Long id);
    Optional<Workshop> findByTenantId(String tenantId);
}