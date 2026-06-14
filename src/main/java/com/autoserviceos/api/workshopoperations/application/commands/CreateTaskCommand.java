package com.autoserviceos.api.workshopoperations.application.commands;

public record CreateTaskCommand(Long workOrderId, Long mechanicId, String description, String priority, Integer estimatedTime, java.math.BigDecimal laborPrice) {}
