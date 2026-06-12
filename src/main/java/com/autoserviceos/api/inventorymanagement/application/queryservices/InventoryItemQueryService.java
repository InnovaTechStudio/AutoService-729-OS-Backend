package com.autoserviceos.api.inventorymanagement.application.queryservices;

import com.autoserviceos.api.inventorymanagement.application.queries.GetAllInventoryItemsQuery;
import com.autoserviceos.api.inventorymanagement.application.queries.GetInventoryItemByIdQuery;
import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InventoryItemQueryService {
    Optional<InventoryItem> handle(GetInventoryItemByIdQuery query);
    List<InventoryItem> handle(GetAllInventoryItemsQuery query);
}
