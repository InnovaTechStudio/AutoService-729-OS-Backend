package com.autoserviceos.api.iam.interfaces.rest.resources;

/**
 * Response payload carrying token tokens profiles metadata returned on success criteria operations.
 */
public record AuthenticatedUserResource(Long id, String email, String role, String workshopId, Long mechanicId, String token) {
}