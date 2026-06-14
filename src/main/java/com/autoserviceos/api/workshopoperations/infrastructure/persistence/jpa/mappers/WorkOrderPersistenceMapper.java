package com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.mappers;

import com.autoserviceos.api.workshopoperations.domain.model.aggregates.WorkOrder;
import com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.entities.WorkOrderJpaEntity;

public final class WorkOrderPersistenceMapper {
    private WorkOrderPersistenceMapper() {}

    public static WorkOrderJpaEntity toJpaEntity(WorkOrder aggregate) {
        return new WorkOrderJpaEntity(
                aggregate.getId(), aggregate.getWorkshopId(), aggregate.getTrackingCode(),
                aggregate.getVehicleId(), aggregate.getCustomerId(), aggregate.getMechanicId(),
                aggregate.getDescription(), aggregate.getStatus(), aggregate.getPrice(),
                aggregate.getEstimatedDate(), aggregate.getStartDate(), aggregate.getTasksCompleted(),
                aggregate.getSparePartsChecked(), aggregate.getDiagnosisValidated(),
                aggregate.getCleaningDone(), aggregate.getFinalTestDone()
        );
    }

    public static WorkOrder toDomain(WorkOrderJpaEntity entity) {
        return WorkOrder.rehydrate(
                entity.getId(), entity.getWorkshopId(), entity.getTrackingCode(),
                entity.getVehicleId(), entity.getCustomerId(), entity.getMechanicId(),
                entity.getDescription(), entity.getStatus(), entity.getPrice(),
                entity.getEstimatedDate(), entity.getStartDate(), entity.getTasksCompleted(),
                entity.getSparePartsChecked(), entity.getDiagnosisValidated(),
                entity.getCleaningDone(), entity.getFinalTestDone()
        );
    }
}