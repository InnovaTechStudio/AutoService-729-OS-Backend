package com.autoserviceos.api.workshopoperations.interfaces.rest;

import com.autoserviceos.api.workshopoperations.application.commands.*;
import com.autoserviceos.api.workshopoperations.application.commandservices.WorkOrderCommandService;
import com.autoserviceos.api.workshopoperations.application.queries.*;
import com.autoserviceos.api.workshopoperations.application.queryservices.WorkOrderQueryService;
import com.autoserviceos.api.workshopoperations.interfaces.rest.resources.*;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Exposes RESTful endpoints for initiating maintenance folders and checklist tracking.
 */
@RestController
@RequestMapping("/api/v1/work-orders")
@Tag(name = "Work Orders", description = "Endpoints for managing vehicle maintenance folders")
public class WorkOrdersController {

    private final WorkOrderCommandService commandService;
    private final WorkOrderQueryService queryService;

    public WorkOrdersController(WorkOrderCommandService commandService, WorkOrderQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    private String getAuthenticatedWorkshopId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getCredentials() instanceof Claims claims) {
            return claims.get("workshopId", String.class);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createWorkOrder(@Valid @RequestBody CreateWorkOrderResource resource) {
        String tenantId = getAuthenticatedWorkshopId();
        if (tenantId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        var command = new CreateWorkOrderCommand(
                tenantId,
                resource.vehicleId(),
                resource.customerId(),
                resource.mechanicId(),
                resource.description(),
                resource.estimatedDate()
        );
        var result = commandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getWorkOrders() {
        String tenantId = getAuthenticatedWorkshopId();
        var workOrders = queryService.handle(new GetWorkOrdersByWorkshopIdQuery(tenantId));
        return ResponseEntity.ok(workOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkOrder(@PathVariable Long id) {
        var workOrder = queryService.handle(new GetWorkOrderByIdQuery(id));
        return workOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkOrder(@PathVariable Long id, @Valid @RequestBody UpdateWorkOrderResource resource) {
        var command = new UpdateWorkOrderCommand(
                id,
                resource.description(),
                resource.estimatedDate(),
                resource.price(),
                resource.status(),
                resource.tasksCompleted(),
                resource.sparePartsChecked(),
                resource.diagnosisValidated(),
                resource.cleaningDone(),
                resource.finalTestDone()
        );
        var result = commandService.handle(command);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}