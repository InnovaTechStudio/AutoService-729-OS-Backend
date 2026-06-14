package com.autoserviceos.api.inventorymanagement.domain.model.repositories;

import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;
import java.util.List;
import java.util.Optional;

/**
 * Domain repository port defining the persistence contract for managing InventoryItem entities.
 */
public interface InventoryItemRepository {
    InventoryItem save(InventoryItem item);
    Optional<InventoryItem> findById(Long id);
    List<InventoryItem> findAll();
    void delete(InventoryItem item);
}