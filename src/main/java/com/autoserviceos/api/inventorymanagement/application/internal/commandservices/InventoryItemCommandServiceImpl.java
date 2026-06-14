package com.autoserviceos.api.inventorymanagement.application.internal.commandservices;

import com.autoserviceos.api.inventorymanagement.application.commandservices.InventoryItemCommandService;
import com.autoserviceos.api.inventorymanagement.application.commands.*;
import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;
import com.autoserviceos.api.inventorymanagement.domain.model.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/** Implementation coordinating inventory item creation, updates, and deletions. */
@Service
public class InventoryItemCommandServiceImpl implements InventoryItemCommandService {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemCommandServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    @Transactional
    public InventoryItem handle(CreateInventoryItemCommand command) {
        var item = InventoryItem.create(command.name(), command.category(), command.brand(),
                command.unitPrice(), command.stock(), command.minStock(), command.image());
        return inventoryItemRepository.save(item);
    }

    @Override
    @Transactional
    public Optional<InventoryItem> handle(UpdateInventoryItemCommand command) {
        var result = inventoryItemRepository.findById(command.id());
        if (result.isEmpty()) return Optional.empty();

        var item = result.get();
        item.update(command.name(), command.category(), command.brand(),
                command.unitPrice(), command.stock(), command.minStock(), command.image());
        return Optional.of(inventoryItemRepository.save(item));
    }

    @Override
    @Transactional
    public void handle(DeleteInventoryItemCommand command) {
        inventoryItemRepository.findById(command.id()).ifPresent(inventoryItemRepository::delete);
    }
}