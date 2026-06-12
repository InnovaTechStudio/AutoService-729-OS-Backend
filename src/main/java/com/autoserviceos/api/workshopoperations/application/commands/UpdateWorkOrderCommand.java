package com.autoserviceos.api.workshopoperations.application.commands;

public record UpdateWorkOrderCommand(Long id, String description, String estimatedDate, java.math.BigDecimal price, String status, Boolean tasksCompleted, Boolean sparePartsChecked, Boolean diagnosisValidated, Boolean cleaningDone, Boolean finalTestDone) {}
