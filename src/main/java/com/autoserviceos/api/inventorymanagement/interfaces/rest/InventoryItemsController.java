package com.autoserviceos.api.inventorymanagement.interfaces.rest;

import com.autoserviceos.api.inventorymanagement.application.commands.DeleteInventoryItemCommand;
import com.autoserviceos.api.inventorymanagement.application.commandservices.InventoryItemCommandService;
import com.autoserviceos.api.inventorymanagement.application.queries.GetAllInventoryItemsQuery;
import com.autoserviceos.api.inventorymanagement.application.queries.GetInventoryItemByIdQuery;
import com.autoserviceos.api.inventorymanagement.application.queryservices.InventoryItemQueryService;
import com.autoserviceos.api.inventorymanagement.interfaces.rest.resources.CreateInventoryItemResource;
import com.autoserviceos.api.inventorymanagement.interfaces.rest.resources.UpdateInventoryItemResource;
import com.autoserviceos.api.inventorymanagement.interfaces.rest.transform.InventoryItemResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Exposes RESTful endpoints for managing warehouse inventory items.
 */
@RestController
@RequestMapping("/api/v1/inventory-items")
@Tag(name = "Inventory Items", description = "Endpoints for managing warehouse catalog and stock")
public class InventoryItemsController {

    private final InventoryItemCommandService inventoryItemCommandService;
    private final InventoryItemQueryService inventoryItemQueryService;

    public InventoryItemsController(InventoryItemCommandService inventoryItemCommandService, InventoryItemQueryService inventoryItemQueryService) {
        this.inventoryItemCommandService = inventoryItemCommandService;
        this.inventoryItemQueryService = inventoryItemQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createInventoryItem(@Valid @RequestBody CreateInventoryItemResource resource) {
        var command = InventoryItemResourceAssembler.toCommandFromResource(resource);
        var item = inventoryItemCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(InventoryItemResourceAssembler.toResourceFromEntity(item));
    }

    @GetMapping
    public ResponseEntity<?> getAllInventoryItems() {
        var items = inventoryItemQueryService.handle(new GetAllInventoryItemsQuery());
        var resources = items.stream().map(InventoryItemResourceAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryItemById(@PathVariable Long id) {
        var item = inventoryItemQueryService.handle(new GetInventoryItemByIdQuery(id));
        if (item.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(InventoryItemResourceAssembler.toResourceFromEntity(item.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventoryItem(@PathVariable Long id, @Valid @RequestBody UpdateInventoryItemResource resource) {
        var command = InventoryItemResourceAssembler.toCommandFromResource(id, resource);
        var item = inventoryItemCommandService.handle(command);
        if (item.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(InventoryItemResourceAssembler.toResourceFromEntity(item.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventoryItem(@PathVariable Long id) {
        inventoryItemCommandService.handle(new DeleteInventoryItemCommand(id));
        return ResponseEntity.noContent().build();
    }
}