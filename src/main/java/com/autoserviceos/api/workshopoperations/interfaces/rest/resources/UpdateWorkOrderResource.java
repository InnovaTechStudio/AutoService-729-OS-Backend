package com.autoserviceos.api.workshopoperations.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Resource blueprint containing data alterations for updating an active work order folder.
 */
public record UpdateWorkOrderResource(
        @NotBlank String description,
        @NotBlank String estimatedDate,
        @NotNull BigDecimal price,
        @NotBlank String status,
        @NotNull Boolean tasksCompleted,
        @NotNull Boolean sparePartsChecked,
        @NotNull Boolean diagnosisValidated,
        @NotNull Boolean cleaningDone,
        @NotNull Boolean finalTestDone
) {}