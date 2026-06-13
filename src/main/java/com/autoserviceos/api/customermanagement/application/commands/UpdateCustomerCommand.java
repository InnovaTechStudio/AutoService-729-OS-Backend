package com.autoserviceos.api.customermanagement.application.commands;

/**
 * Command to request the update of an existing customer's details.
 */
public record UpdateCustomerCommand(Long id, String fullName, String dni, String email, String phone) {
}