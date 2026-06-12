package com.autoserviceos.api.inventorymanagement.application.commands;

import java.math.BigDecimal;

public record CreateInventoryItemCommand(String name, String category, String brand,
                                         BigDecimal unitPrice, Integer stock, Integer minStock, String image) {}
