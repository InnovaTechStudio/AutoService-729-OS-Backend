package com.autoserviceos.api.workshopoperations.interfaces.rest.resources;

public record PatchTaskResource(
        String status,
        String technicalDiagnosis,
        String customerExplanation,
        String internalObservation,
        String evidenceRegistered,
        String adminReviewStatus
) {}
