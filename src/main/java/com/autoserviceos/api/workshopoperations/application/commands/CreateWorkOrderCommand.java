package com.autoserviceos.api.workshopoperations.application.commands;

public record CreateWorkOrderCommand(String workshopId, Long vehicleId, Long customerId, Long mechanicId, String description, String estimatedDate) {}
