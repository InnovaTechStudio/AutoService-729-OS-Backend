package com.autoserviceos.api.customermanagement.infrastructure.persistence.jpa.mappers;

import com.autoserviceos.api.customermanagement.domain.model.aggregates.Customer;
import com.autoserviceos.api.customermanagement.infrastructure.persistence.jpa.entities.CustomerJpaEntity;

/**
 * Mapper utility class to convert between the Domain Aggregate and the JPA Entity.
 */
public final class CustomerPersistenceMapper {

    private CustomerPersistenceMapper() {}

    /**
     * Converts a domain Customer aggregate to a JPA entity.
     */
    public static CustomerJpaEntity toJpaEntity(Customer aggregate) {
        return new CustomerJpaEntity(
                aggregate.getId(),
                aggregate.getWorkshopId(),
                aggregate.getFullName(),
                aggregate.getDni(),
                aggregate.getEmail(),
                aggregate.getPhone()
        );
    }

    /**
     * Converts a JPA entity back to a domain Customer aggregate.
     */
    public static Customer toDomain(CustomerJpaEntity entity) {
        return Customer.rehydrate(
                entity.getId(),
                entity.getWorkshopId(),
                entity.getFullName(),
                entity.getDni(),
                entity.getEmail(),
                entity.getPhone()
        );
    }
}