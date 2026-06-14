package com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa.mappers;

import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa.entities.MechanicJpaEntity;

public final class MechanicPersistenceMapper {
    private MechanicPersistenceMapper() {}

    public static MechanicJpaEntity toJpaEntity(Mechanic aggregate) {
        return new MechanicJpaEntity(aggregate.getId(), aggregate.getFullName(), aggregate.getSpecialty(),
                aggregate.getMaxCapacity(), aggregate.getEmail(), aggregate.getWorkshopId());
    }

    public static Mechanic toDomain(MechanicJpaEntity entity) {
        return Mechanic.rehydrate(entity.getId(), entity.getFullName(), entity.getSpecialty(),
                entity.getMaxCapacity(), entity.getEmail(), entity.getWorkshopId());
    }
}