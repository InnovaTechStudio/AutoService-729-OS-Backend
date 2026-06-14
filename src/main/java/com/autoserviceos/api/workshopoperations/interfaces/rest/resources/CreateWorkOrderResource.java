package com.autoserviceos.api.workshopoperations.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWorkOrderResource(
        @NotNull Long vehicleId,
        @NotNull Long customerId,
        @NotNull Long mechanicId,
        @NotBlank String description,
        @NotBlank String estimatedDate
) {}
