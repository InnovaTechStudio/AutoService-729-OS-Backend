package com.autoserviceos.api.workshopoperations.interfaces.rest.resources;

/**
 * Envelope structure carrying delta metrics properties used to patch technical data updates.
 */
public record PatchTaskResource(
        String status,
        String technicalDiagnosis,
        String customerExplanation,
        String internalObservation,
        String evidenceRegistered,
        String adminReviewStatus
) {}