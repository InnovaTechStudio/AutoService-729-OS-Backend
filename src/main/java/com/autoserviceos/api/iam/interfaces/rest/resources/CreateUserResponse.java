package com.autoserviceos.api.iam.interfaces.rest.resources;

/**
 * Representation instance tracking response status confirmation indicators.
 */
public record CreateUserResponse(String message, Long userId) {
}