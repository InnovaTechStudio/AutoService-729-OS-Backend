package com.autoserviceos.api.customermanagement.application.commands;

/**
 * Command to request the deletion of a customer by their unique identifier.
 */
public record DeleteCustomerCommand(Long id) {
}