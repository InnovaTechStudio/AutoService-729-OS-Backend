package com.autoserviceos.api.publictracking.interfaces.rest.resources;

/**
 * Lightweight response payload exposing limited workshop details for public tracking views.
 */
public record TrackingWorkshopResource(Long id, String name) {
}