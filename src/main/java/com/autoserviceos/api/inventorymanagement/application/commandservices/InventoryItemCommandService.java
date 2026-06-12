package com.autoserviceos.api.inventorymanagement.application.commandservices;

import com.autoserviceos.api.inventorymanagement.application.commands.CreateInventoryItemCommand;
import com.autoserviceos.api.inventorymanagement.application.commands.DeleteInventoryItemCommand;
import com.autoserviceos.api.inventorymanagement.application.commands.UpdateInventoryItemCommand;
import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;

import java.util.Optional;

public interface InventoryItemCommandService {
    InventoryItem handle(CreateInventoryItemCommand command);
    Optional<InventoryItem> handle(UpdateInventoryItemCommand command);
    void handle(DeleteInventoryItemCommand command);
}
