package com.autoserviceos.api.fleetmanagement.application.commands;
/** Command to register a new vehicle into the fleet management context. */
public record CreateVehicleCommand(String plate, String brand, String model, String year, String color, String status, String image, Long customerId) {}