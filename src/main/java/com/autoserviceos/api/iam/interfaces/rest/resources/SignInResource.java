package com.autoserviceos.api.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * Request criteria record payload mapping login identification credentials parameters.
 */
public record SignInResource(@NotBlank String email, @NotBlank String password) {
}