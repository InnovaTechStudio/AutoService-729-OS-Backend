package com.autoserviceos.api.inventorymanagement.interfaces.rest.resources;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record UpdateInventoryItemResource(
        @NotBlank String name, @NotBlank String category, @NotBlank String brand,
        @NotNull @Min(0) BigDecimal unitPrice, @NotNull @Min(0) Integer stock, @NotNull @Min(0) Integer minStock,
        String image
) {}