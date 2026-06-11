package com.autoserviceos.api.tenantmanagement.interfaces.rest.resources;

public record CreateWorkshopResource(
        String name,
        String ruc,
        String address,
        String phone,
        String email
) {
}