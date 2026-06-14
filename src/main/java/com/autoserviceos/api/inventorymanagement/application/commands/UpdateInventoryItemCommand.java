package com.autoserviceos.api.inventorymanagement.application.commands;
import java.math.BigDecimal;
/** Command to update an existing inventory item's specifications and constraints. */
public record UpdateInventoryItemCommand(Long id, String name, String category, String brand,
                                         BigDecimal unitPrice, Integer stock, Integer minStock, String image) {}