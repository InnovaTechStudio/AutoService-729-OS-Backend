package com.autoserviceos.api.publictracking.interfaces.rest.resources;

/**
 * Lightweight response payload exposing limited customer details for public tracking views.
 */
public record TrackingCustomerResource(Long id, String fullName) {
}