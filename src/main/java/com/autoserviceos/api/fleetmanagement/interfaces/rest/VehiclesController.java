package com.autoserviceos.api.fleetmanagement.interfaces.rest;

import com.autoserviceos.api.customermanagement.application.queries.GetAllCustomersByWorkshopIdQuery;
import com.autoserviceos.api.customermanagement.application.queryservices.CustomerQueryService;
import com.autoserviceos.api.fleetmanagement.application.commands.DeleteVehicleCommand;
import com.autoserviceos.api.fleetmanagement.application.commandservices.VehicleCommandService;
import com.autoserviceos.api.fleetmanagement.application.queries.GetAllVehiclesQuery;
import com.autoserviceos.api.fleetmanagement.application.queries.GetVehicleByIdQuery;
import com.autoserviceos.api.fleetmanagement.application.queryservices.VehicleQueryService;
import com.autoserviceos.api.fleetmanagement.interfaces.rest.resources.CreateVehicleResource;
import com.autoserviceos.api.fleetmanagement.interfaces.rest.resources.UpdateVehicleResource;
import com.autoserviceos.api.fleetmanagement.interfaces.rest.transform.VehicleResourceAssembler;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Exposes RESTful endpoints for managing vehicles inside the fleet context.
 */
@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicles", description = "Endpoints for managing workshop fleet vehicles")
public class VehiclesController {

    private final VehicleCommandService vehicleCommandService;
    private final VehicleQueryService vehicleQueryService;
    private final CustomerQueryService customerQueryService;

    public VehiclesController(VehicleCommandService vehicleCommandService, VehicleQueryService vehicleQueryService, CustomerQueryService customerQueryService) {
        this.vehicleCommandService = vehicleCommandService;
        this.vehicleQueryService = vehicleQueryService;
        this.customerQueryService = customerQueryService;
    }

    /**
     * Extracts the WorkshopId from the current authenticated user's JWT token.
     */
    private String getAuthenticatedWorkshopId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof Claims claims) {
            return claims.get("workshopId", String.class);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createVehicle(@Valid @RequestBody CreateVehicleResource resource) {
        var command = VehicleResourceAssembler.toCommandFromResource(resource);
        var vehicle = vehicleCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(VehicleResourceAssembler.toResourceFromEntity(vehicle));
    }

    @GetMapping
    public ResponseEntity<?> getAllVehicles() {
        String workshopId = getAuthenticatedWorkshopId();
        if (workshopId == null || workshopId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Fetch all vehicles and filter them by the current workshop's customers
        var allVehicles = vehicleQueryService.handle(new GetAllVehiclesQuery());
        var workshopCustomers = customerQueryService.handle(new GetAllCustomersByWorkshopIdQuery(workshopId));

        List<Long> validCustomerIds = workshopCustomers.stream()
                .map(customer -> customer.getId())
                .toList();

        var filteredResources = allVehicles.stream()
                .filter(v -> validCustomerIds.contains(v.getCustomerId()))
                .map(VehicleResourceAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(filteredResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        var vehicle = vehicleQueryService.handle(new GetVehicleByIdQuery(id));
        if (vehicle.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(VehicleResourceAssembler.toResourceFromEntity(vehicle.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @Valid @RequestBody UpdateVehicleResource resource) {
        var command = VehicleResourceAssembler.toCommandFromResource(id, resource);
        var vehicle = vehicleCommandService.handle(command);
        if (vehicle.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(VehicleResourceAssembler.toResourceFromEntity(vehicle.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        vehicleCommandService.handle(new DeleteVehicleCommand(id));
        return ResponseEntity.noContent().build();
    }
}