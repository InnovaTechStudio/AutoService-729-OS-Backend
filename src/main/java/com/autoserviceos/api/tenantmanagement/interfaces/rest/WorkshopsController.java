package com.autoserviceos.api.tenantmanagement.interfaces.rest;

import com.autoserviceos.api.tenantmanagement.application.internal.WorkshopCommandService;
import com.autoserviceos.api.tenantmanagement.application.internal.WorkshopQueryService;
import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;
import com.autoserviceos.api.tenantmanagement.interfaces.rest.resources.CreateWorkshopResource;
import com.autoserviceos.api.tenantmanagement.interfaces.rest.resources.WorkshopResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshops")
public class WorkshopsController {

    private final WorkshopQueryService workshopQueryService;
    private final WorkshopCommandService workshopCommandService;

    public WorkshopsController(WorkshopQueryService workshopQueryService, WorkshopCommandService workshopCommandService) {
        this.workshopQueryService = workshopQueryService;
        this.workshopCommandService = workshopCommandService;
    }

    @GetMapping
    public ResponseEntity<List<WorkshopResource>> getAllWorkshops() {
        var workshops = workshopQueryService.getAllWorkshops()
                .stream()
                .map(this::toResource)
                .toList();

        return ResponseEntity.ok(workshops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkshopResource> getWorkshopById(@PathVariable Long id) {
        return workshopQueryService.getWorkshopById(id)
                .map(workshop -> ResponseEntity.ok(toResource(workshop)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WorkshopResource> createWorkshop(@RequestBody CreateWorkshopResource resource) {
        var workshop = workshopCommandService.createWorkshop(resource);
        return ResponseEntity.ok(toResource(workshop));
    }

    private WorkshopResource toResource(Workshop workshop) {
        return new WorkshopResource(
                workshop.getId(),
                workshop.getName(),
                workshop.getRuc(),
                workshop.getAddress(),
                workshop.getPhone(),
                workshop.getEmail(),
                workshop.getActive()
        );
    }
}