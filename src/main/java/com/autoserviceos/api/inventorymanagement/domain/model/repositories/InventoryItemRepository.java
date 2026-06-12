package com.autoserviceos.api.inventorymanagement.domain.model.repositories;

import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository {
    InventoryItem save(InventoryItem item);
    Optional<InventoryItem> findById(Long id);
    List<InventoryItem> findAll();
    void delete(InventoryItem item);
}