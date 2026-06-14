package com.autoserviceos.api.inventorymanagement.application.internal.queryservices;

import com.autoserviceos.api.inventorymanagement.application.queryservices.InventoryItemQueryService;
import com.autoserviceos.api.inventorymanagement.application.queries.*;
import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;
import com.autoserviceos.api.inventorymanagement.domain.model.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/** Implementation executing read-only database queries for inventory catalog information. */
@Service
@Transactional(readOnly = true)
public class InventoryItemQueryServiceImpl implements InventoryItemQueryService {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemQueryServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public Optional<InventoryItem> handle(GetInventoryItemByIdQuery query) {
        return inventoryItemRepository.findById(query.id());
    }

    @Override
    public List<InventoryItem> handle(GetAllInventoryItemsQuery query) {
        return inventoryItemRepository.findAll();
    }
}