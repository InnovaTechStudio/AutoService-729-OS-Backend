package com.autoserviceos.api.staffcoordination.interfaces.rest;

import com.autoserviceos.api.staffcoordination.application.commands.DeleteMechanicCommand;
import com.autoserviceos.api.staffcoordination.application.commandservices.MechanicCommandService;
import com.autoserviceos.api.staffcoordination.application.queries.GetAllMechanicsByWorkshopIdQuery;
import com.autoserviceos.api.staffcoordination.application.queries.GetMechanicByIdQuery;
import com.autoserviceos.api.staffcoordination.application.queryservices.MechanicQueryService;
import com.autoserviceos.api.staffcoordination.interfaces.rest.resources.CreateMechanicResource;
import com.autoserviceos.api.staffcoordination.interfaces.rest.resources.UpdateMechanicResource;
import com.autoserviceos.api.staffcoordination.interfaces.rest.transform.MechanicResourceAssembler;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Exposes RESTful endpoints for managing technical staff mechanics profiles within a workshop.
 */
@RestController
@RequestMapping("/api/v1/mechanics")
@Tag(name = "Mechanics", description = "Endpoints for managing workshop technical staff and capabilities")
public class MechanicsController {

    private final MechanicCommandService mechanicCommandService;
    private final MechanicQueryService mechanicQueryService;

    public MechanicsController(MechanicCommandService mechanicCommandService, MechanicQueryService mechanicQueryService) {
        this.mechanicCommandService = mechanicCommandService;
        this.mechanicQueryService = mechanicQueryService;
    }

    /** Extracts the WorkshopId from the current authenticated user's JWT token. */
    private String getAuthenticatedWorkshopId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof Claims claims) {
            return claims.get("workshopId", String.class);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createMechanic(@Valid @RequestBody CreateMechanicResource resource) {
        String workshopId = getAuthenticatedWorkshopId();
        if (workshopId == null || workshopId.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try {
            var command = MechanicResourceAssembler.toCommandFromResource(resource, workshopId);
            var mechanic = mechanicCommandService.handle(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(MechanicResourceAssembler.toResourceFromEntity(mechanic));
        } catch (IllegalArgumentException e) {
            // Catches the exception from IAM if the email is already registered
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllMechanics() {
        String workshopId = getAuthenticatedWorkshopId();
        if (workshopId == null || workshopId.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        var mechanics = mechanicQueryService.handle(new GetAllMechanicsByWorkshopIdQuery(workshopId));
        var resources = mechanics.stream().map(MechanicResourceAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMechanicById(@PathVariable Long id) {
        var mechanic = mechanicQueryService.handle(new GetMechanicByIdQuery(id));
        if (mechanic.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(MechanicResourceAssembler.toResourceFromEntity(mechanic.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMechanic(@PathVariable Long id, @Valid @RequestBody UpdateMechanicResource resource) {
        var command = MechanicResourceAssembler.toCommandFromResource(id, resource);
        var mechanic = mechanicCommandService.handle(command);
        if (mechanic.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(MechanicResourceAssembler.toResourceFromEntity(mechanic.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMechanic(@PathVariable Long id) {
        mechanicCommandService.handle(new DeleteMechanicCommand(id));
        return ResponseEntity.noContent().build();
    }
}