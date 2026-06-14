package com.autoserviceos.api.inventorymanagement.application.commands;
/** Command to remove an inventory item from the catalog data storage. */
public record DeleteInventoryItemCommand(Long id) {}