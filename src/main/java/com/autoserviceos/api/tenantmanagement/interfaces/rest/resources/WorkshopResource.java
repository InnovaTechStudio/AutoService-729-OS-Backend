package com.autoserviceos.api.tenantmanagement.interfaces.rest.resources;

public record WorkshopResource(
        Long id,
        String name,
        String ruc,
        String address,
        String phone,
        String email,
        Boolean active
) {
}