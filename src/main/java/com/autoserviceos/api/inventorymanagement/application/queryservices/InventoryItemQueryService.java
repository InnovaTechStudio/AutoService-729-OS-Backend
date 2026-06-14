package com.autoserviceos.api.inventorymanagement.application.queryservices;

import com.autoserviceos.api.inventorymanagement.application.queries.*;
import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;
import java.util.List;
import java.util.Optional;

/** Service contract for managing inventory item domain read operations. */
public interface InventoryItemQueryService {
    Optional<InventoryItem> handle(GetInventoryItemByIdQuery query);
    List<InventoryItem> handle(GetAllInventoryItemsQuery query);
}