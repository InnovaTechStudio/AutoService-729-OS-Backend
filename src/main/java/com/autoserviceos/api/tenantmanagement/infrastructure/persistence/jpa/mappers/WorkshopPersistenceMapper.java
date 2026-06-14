package com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa.mappers;

import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;
import com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa.entities.WorkshopJpaEntity;

/**
 * Utility mapper converting instances between Domain Aggregates and JPA Entities.
 */
public final class WorkshopPersistenceMapper {

    private WorkshopPersistenceMapper() {}

    public static WorkshopJpaEntity toJpaEntity(Workshop aggregate) {
        return new WorkshopJpaEntity(
                aggregate.getId(),
                aggregate.getName(),
                aggregate.getTenantId()
        );
    }

    public static Workshop toDomain(WorkshopJpaEntity entity) {
        return Workshop.rehydrate(
                entity.getId(),
                entity.getName(),
                entity.getTenantId()
        );
    }
}