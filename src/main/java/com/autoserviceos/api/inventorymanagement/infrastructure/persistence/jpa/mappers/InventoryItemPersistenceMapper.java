package com.autoserviceos.api.inventorymanagement.infrastructure.persistence.jpa.mappers;

import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;
import com.autoserviceos.api.inventorymanagement.infrastructure.persistence.jpa.entities.InventoryItemJpaEntity;

public final class InventoryItemPersistenceMapper {
    private InventoryItemPersistenceMapper() {}

    public static InventoryItemJpaEntity toJpaEntity(InventoryItem aggregate) {
        return new InventoryItemJpaEntity(aggregate.getId(), aggregate.getSku(), aggregate.getName(),
                aggregate.getCategory(), aggregate.getBrand(), aggregate.getUnitPrice(),
                aggregate.getStock(), aggregate.getMinStock(), aggregate.getImage()); // ← agregar
    }

    public static InventoryItem toDomain(InventoryItemJpaEntity entity) {
        return InventoryItem.rehydrate(entity.getId(), entity.getSku(), entity.getName(),
                entity.getCategory(), entity.getBrand(), entity.getUnitPrice(),
                entity.getStock(), entity.getMinStock(), entity.getImage()); // ← agregar
    }
}