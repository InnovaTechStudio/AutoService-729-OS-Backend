package com.autoserviceos.api.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * Request payload structure used to create a company account scope alongside its primary administrator credentials.
 */
public record SignUpWorkshopResource(@NotBlank String workshopName, @NotBlank String email, @NotBlank String password) {
}