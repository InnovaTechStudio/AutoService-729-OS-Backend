package com.autoserviceos.api.workshopoperations.application.commands;

/**
 * Command to request a partial update of a work order's status (e.g. marking it as delivered).
 */
public record PatchWorkOrderCommand(Long id, String status) {}
