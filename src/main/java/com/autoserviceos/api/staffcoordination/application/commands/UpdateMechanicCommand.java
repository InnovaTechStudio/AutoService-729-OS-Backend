package com.autoserviceos.api.staffcoordination.application.commands;
/** Command to update an existing mechanic record profile properties. */
public record UpdateMechanicCommand(Long id, String fullName, String specialty, Integer maxCapacity, String email) {}