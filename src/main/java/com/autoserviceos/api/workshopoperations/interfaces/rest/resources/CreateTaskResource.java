package com.autoserviceos.api.workshopoperations.interfaces.rest.resources;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Data transfer object payload required to instantiate and register an execution task.
 */
public record CreateTaskResource(
        @NotNull Long workOrderId,
        Long mechanicId,
        @NotBlank String description,
        @NotBlank String priority,
        @NotNull @Min(1) Integer estimatedTime,
        @NotNull @Min(0) BigDecimal laborPrice
) {}