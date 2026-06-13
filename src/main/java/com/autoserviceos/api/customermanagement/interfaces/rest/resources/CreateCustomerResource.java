package com.autoserviceos.api.customermanagement.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO used to create a new customer.
 */
public record CreateCustomerResource(
        @NotBlank String fullName,
        @NotBlank String dni,
        String email,
        String phone
) {}