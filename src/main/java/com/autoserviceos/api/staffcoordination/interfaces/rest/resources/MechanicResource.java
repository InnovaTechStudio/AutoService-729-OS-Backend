package com.autoserviceos.api.staffcoordination.interfaces.rest.resources;

public record MechanicResource(
        Long id, String fullName, String specialty,
        Integer maxCapacity, String email, String workshopId
) {}