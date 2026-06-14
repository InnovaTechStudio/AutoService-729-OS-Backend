package com.autoserviceos.api.inventorymanagement.application.commandservices;

import com.autoserviceos.api.inventorymanagement.application.commands.*;
import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;
import java.util.Optional;

/** Service contract for managing inventory item domain write operations. */
public interface InventoryItemCommandService {
    InventoryItem handle(CreateInventoryItemCommand command);
    Optional<InventoryItem> handle(UpdateInventoryItemCommand command);
    void handle(DeleteInventoryItemCommand command);
}