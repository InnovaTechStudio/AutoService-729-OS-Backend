package com.autoserviceos.api.staffcoordination.interfaces.rest.resources;

public record CreateMechanicResource(
        String firstName,
        String lastName,
        String email,
        String phone,
        String specialty,
        Long workshopId
) {
}