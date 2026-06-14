package com.autoserviceos.api.tenantmanagement.application.commands;

/**
 * Command containing the required details to initialize a new workshop tenant.
 */
public record CreateWorkshopCommand(String workshopName) {
}