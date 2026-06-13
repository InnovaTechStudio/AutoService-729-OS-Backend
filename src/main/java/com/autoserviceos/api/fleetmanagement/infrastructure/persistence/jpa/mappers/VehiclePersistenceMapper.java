package com.autoserviceos.api.fleetmanagement.infrastructure.persistence.jpa.mappers;

import com.autoserviceos.api.fleetmanagement.domain.model.aggregates.Vehicle;
import com.autoserviceos.api.fleetmanagement.infrastructure.persistence.jpa.entities.VehicleJpaEntity;

public final class VehiclePersistenceMapper {
    private VehiclePersistenceMapper() {}

    public static VehicleJpaEntity toJpaEntity(Vehicle aggregate) {
        return new VehicleJpaEntity(aggregate.getId(), aggregate.getPlate(), aggregate.getBrand(), aggregate.getModel(),
                aggregate.getYear(), aggregate.getColor(), aggregate.getStatus(), aggregate.getImage(), aggregate.getCustomerId());
    }

    public static Vehicle toDomain(VehicleJpaEntity entity) {
        return Vehicle.rehydrate(entity.getId(), entity.getPlate(), entity.getBrand(), entity.getModel(),
                entity.getYear(), entity.getColor(), entity.getStatus(), entity.getImage(), entity.getCustomerId());
    }
}