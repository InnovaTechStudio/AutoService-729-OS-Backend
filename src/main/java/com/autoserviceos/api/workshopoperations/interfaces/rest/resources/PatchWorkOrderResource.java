package com.autoserviceos.api.workshopoperations.interfaces.rest.resources;

/**
 * Envelope structure carrying a partial status update for a work order.
 */
public record PatchWorkOrderResource(String status) {}
