package com.autoserviceos.api.staffcoordination.interfaces.rest.resources;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMechanicResource(
        @NotBlank String fullName, @NotBlank String specialty,
        @NotNull @Min(1) Integer maxCapacity, @NotBlank @Email String email, @NotBlank String password
) {}