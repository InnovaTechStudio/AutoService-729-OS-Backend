package com.autoserviceos.api.workshopoperations.interfaces.rest;

import com.autoserviceos.api.workshopoperations.application.commands.*;
import com.autoserviceos.api.workshopoperations.application.commandservices.TaskCommandService;
import com.autoserviceos.api.workshopoperations.application.queries.*;
import com.autoserviceos.api.workshopoperations.application.queryservices.TaskQueryService;
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
 * Exposes RESTful endpoints for managing granular maintenance tasks and technician status logs.
 */
@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Tasks", description = "Endpoints for granular maintenance tasks")
public class TasksController {

    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;
    private final WorkOrderQueryService workOrderQueryService;

    public TasksController(TaskCommandService taskCommandService, TaskQueryService taskQueryService, WorkOrderQueryService workOrderQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
        this.workOrderQueryService = workOrderQueryService;
    }

    private String getAuthenticatedWorkshopId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getCredentials() instanceof Claims claims) {
            return claims.get("workshopId", String.class);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody CreateTaskResource resource) {
        var command = new CreateTaskCommand(
                resource.workOrderId(),
                resource.mechanicId(),
                resource.description(),
                resource.priority(),
                resource.estimatedTime(),
                resource.laborPrice()
        );
        var result = taskCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getTasks(@RequestParam(required = false) Long workOrderId, @RequestParam(required = false) Long mechanicId) {
        String workshopId = getAuthenticatedWorkshopId();
        if (workshopId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        if (workOrderId != null) {
            return ResponseEntity.ok(taskQueryService.handle(new GetTasksByWorkOrderIdQuery(workOrderId)));
        } else if (mechanicId != null) {
            return ResponseEntity.ok(taskQueryService.handle(new GetTasksByMechanicIdQuery(mechanicId)));
        } else {
            var allTasks = taskQueryService.handle(new GetAllTasksQuery());
            var validWorkOrders = workOrderQueryService.handle(new GetWorkOrdersByWorkshopIdQuery(workshopId))
                    .stream().map(wo -> wo.getId()).toList();
            var filteredTasks = allTasks.stream().filter(t -> validWorkOrders.contains(t.getWorkOrderId())).toList();
            return ResponseEntity.ok(filteredTasks);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskResource resource) {
        var command = new UpdateTaskCommand(
                id,
                resource.description(),
                resource.status(),
                resource.priority(),
                resource.estimatedTime(),
                resource.laborPrice(),
                resource.mechanicId()
        );
        var result = taskCommandService.handle(command);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchTask(@PathVariable Long id, @RequestBody PatchTaskResource resource) {
        var command = new PatchTaskCommand(
                id,
                resource.status(),
                resource.technicalDiagnosis(),
                resource.customerExplanation(),
                resource.internalObservation(),
                resource.evidenceRegistered(),
                resource.adminReviewStatus()
        );
        var result = taskCommandService.handle(command);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskCommandService.handle(new DeleteTaskCommand(id));
        return ResponseEntity.noContent().build();
    }
}