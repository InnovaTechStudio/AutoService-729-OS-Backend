package com.autoserviceos.api.workshopoperations.application.commands;

public record UpdateTaskCommand(Long id, String description, String status, String priority, Integer estimatedTime, java.math.BigDecimal laborPrice, Long mechanicId) {}
