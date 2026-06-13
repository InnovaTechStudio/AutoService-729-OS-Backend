package com.autoserviceos.api.customermanagement.application.commands;

/**
 * Command to request the creation of a new customer.
 */
public record CreateCustomerCommand(String workshopId, String fullName, String dni, String email, String phone) {
}