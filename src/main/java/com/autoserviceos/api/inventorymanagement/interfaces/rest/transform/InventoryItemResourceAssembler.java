package com.autoserviceos.api.inventorymanagement.interfaces.rest.transform;

import com.autoserviceos.api.inventorymanagement.application.commands.CreateInventoryItemCommand;
import com.autoserviceos.api.inventorymanagement.application.commands.UpdateInventoryItemCommand;
import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;
import com.autoserviceos.api.inventorymanagement.interfaces.rest.resources.CreateInventoryItemResource;
import com.autoserviceos.api.inventorymanagement.interfaces.rest.resources.InventoryItemResource;
import com.autoserviceos.api.inventorymanagement.interfaces.rest.resources.UpdateInventoryItemResource;

public class InventoryItemResourceAssembler {
    public static InventoryItemResource toResourceFromEntity(InventoryItem entity) {
        return new InventoryItemResource(entity.getId(), entity.getSku(), entity.getName(),
                entity.getCategory(), entity.getBrand(), entity.getUnitPrice(),
                entity.getStock(), entity.getMinStock(), entity.getImage());
    }

    public static CreateInventoryItemCommand toCommandFromResource(CreateInventoryItemResource resource) {
        return new CreateInventoryItemCommand(resource.name(), resource.category(), resource.brand(),
                resource.unitPrice(), resource.stock(), resource.minStock(), resource.image() != null ? resource.image() : "");
    }

    public static UpdateInventoryItemCommand toCommandFromResource(Long id, UpdateInventoryItemResource resource) {
        return new UpdateInventoryItemCommand(id, resource.name(), resource.category(), resource.brand(),
                resource.unitPrice(), resource.stock(), resource.minStock(), resource.image() != null ? resource.image() : "");
    }
}