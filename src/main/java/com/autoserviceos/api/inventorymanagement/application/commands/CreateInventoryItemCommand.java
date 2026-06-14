package com.autoserviceos.api.inventorymanagement.application.commands;
import java.math.BigDecimal;
/** Command to register a new inventory item into the global catalog. */
public record CreateInventoryItemCommand(String name, String category, String brand,
                                         BigDecimal unitPrice, Integer stock, Integer minStock, String image) {}