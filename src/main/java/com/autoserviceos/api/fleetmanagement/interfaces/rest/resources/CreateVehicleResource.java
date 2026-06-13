package com.autoserviceos.api.fleetmanagement.interfaces.rest.resources;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record CreateVehicleResource(@NotBlank String plate, @NotBlank String brand, @NotBlank String model, @NotBlank String year, String color, @NotBlank String status, String image, @NotNull Long customerId) {}