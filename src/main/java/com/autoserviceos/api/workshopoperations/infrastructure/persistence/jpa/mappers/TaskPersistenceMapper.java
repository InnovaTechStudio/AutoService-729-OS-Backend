package com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.mappers;

import com.autoserviceos.api.workshopoperations.domain.model.aggregates.Task;
import com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.entities.TaskJpaEntity;

public final class TaskPersistenceMapper {
    private TaskPersistenceMapper() {}

    public static TaskJpaEntity toJpaEntity(Task aggregate) {
        return new TaskJpaEntity(
                aggregate.getId(), aggregate.getWorkOrderId(), aggregate.getMechanicId(),
                aggregate.getDescription(), aggregate.getStatus(), aggregate.getPriority(),
                aggregate.getEstimatedTime(), aggregate.getLaborPrice(), aggregate.getTechnicalDiagnosis(),
                aggregate.getCustomerExplanation(), aggregate.getInternalObservation(),
                aggregate.getEvidenceRegistered(), aggregate.getAdminReviewStatus()
        );
    }

    public static Task toDomain(TaskJpaEntity entity) {
        return Task.rehydrate(
                entity.getId(), entity.getWorkOrderId(), entity.getMechanicId(),
                entity.getDescription(), entity.getStatus(), entity.getPriority(),
                entity.getEstimatedTime(), entity.getLaborPrice(), entity.getTechnicalDiagnosis(),
                entity.getCustomerExplanation(), entity.getInternalObservation(),
                entity.getEvidenceRegistered(), entity.getAdminReviewStatus()
        );
    }
}