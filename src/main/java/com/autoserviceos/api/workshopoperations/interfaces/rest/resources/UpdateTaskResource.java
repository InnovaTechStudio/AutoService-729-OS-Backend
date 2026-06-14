package com.autoserviceos.api.workshopoperations.interfaces.rest.resources;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO carrying full structural parameter state updates for an active task row.
 */
public record UpdateTaskResource(
        @NotBlank String description,
        @NotBlank String status,
        @NotBlank String priority,
        @NotNull @Min(1) Integer estimatedTime,
        @NotNull @Min(0) BigDecimal laborPrice,
        Long mechanicId
) {}