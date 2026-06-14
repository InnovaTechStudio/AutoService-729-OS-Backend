package com.autoserviceos.api.staffcoordination.application.commands;
/** Command to register a new mechanic profile, including credentials generation payload. */
public record CreateMechanicCommand(String fullName, String specialty, Integer maxCapacity, String email, String password, String workshopId) {}