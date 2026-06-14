package com.autoserviceos.api.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * Request model carrying registration data definitions used to initialize basic user profiles.
 */
public record SignUpResource(@NotBlank String email, @NotBlank String password, @NotBlank String role, @NotBlank String workshopId) {
}