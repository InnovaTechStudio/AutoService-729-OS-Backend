package com.autoserviceos.api.staffcoordination.interfaces.rest.resources;

public record MechanicResource(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String specialty,
        Long workshopId,
        Boolean active
) {
}