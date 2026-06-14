package com.autoserviceos.api.inventorymanagement.interfaces.rest.resources;
import java.math.BigDecimal;

public record InventoryItemResource(
        Long id, String sku, String name, String category, String brand,
        BigDecimal unitPrice, Integer stock, Integer minStock, String image
) {}