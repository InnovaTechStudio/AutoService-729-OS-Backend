package com.autoserviceos.api.customermanagement.interfaces.rest.resources;

/**
 * Response DTO representing customer data.
 */
public record CustomerResource(
        Long id,
        String workshopId,
        String fullName,
        String dni,
        String email,
        String phone
) {}