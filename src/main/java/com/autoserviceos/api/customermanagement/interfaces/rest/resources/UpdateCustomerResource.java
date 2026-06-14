package com.autoserviceos.api.customermanagement.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO used to update an existing customer.
 */
public record UpdateCustomerResource(
        @NotBlank String fullName,
        @NotBlank String dni,
        String email,
        String phone
) {}