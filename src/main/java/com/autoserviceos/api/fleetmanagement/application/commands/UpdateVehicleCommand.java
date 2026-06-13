package com.autoserviceos.api.fleetmanagement.application.commands;
/** Command to update an existing vehicle's details. */
public record UpdateVehicleCommand(Long id, String plate, String brand, String model, String year, String color, String status, String image, Long customerId) {}