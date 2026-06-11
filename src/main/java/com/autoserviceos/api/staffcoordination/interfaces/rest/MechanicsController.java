package com.autoserviceos.api.staffcoordination.interfaces.rest;

import com.autoserviceos.api.staffcoordination.application.internal.MechanicCommandService;
import com.autoserviceos.api.staffcoordination.application.internal.MechanicQueryService;
import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import com.autoserviceos.api.staffcoordination.interfaces.rest.resources.CreateMechanicResource;
import com.autoserviceos.api.staffcoordination.interfaces.rest.resources.MechanicResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mechanics")
public class MechanicsController {

    private final MechanicQueryService mechanicQueryService;
    private final MechanicCommandService mechanicCommandService;

    public MechanicsController(MechanicQueryService mechanicQueryService, MechanicCommandService mechanicCommandService) {
        this.mechanicQueryService = mechanicQueryService;
        this.mechanicCommandService = mechanicCommandService;
    }

    @GetMapping
    public ResponseEntity<List<MechanicResource>> getAllMechanics() {
        var mechanics = mechanicQueryService.getAllMechanics()
                .stream()
                .map(this::toResource)
                .toList();

        return ResponseEntity.ok(mechanics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MechanicResource> getMechanicById(@PathVariable Long id) {
        return mechanicQueryService.getMechanicById(id)
                .map(mechanic -> ResponseEntity.ok(toResource(mechanic)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MechanicResource> createMechanic(@RequestBody CreateMechanicResource resource) {
        var mechanic = mechanicCommandService.createMechanic(resource);
        return ResponseEntity.ok(toResource(mechanic));
    }

    private MechanicResource toResource(Mechanic mechanic) {
        return new MechanicResource(
                mechanic.getId(),
                mechanic.getFirstName(),
                mechanic.getLastName(),
                mechanic.getEmail(),
                mechanic.getPhone(),
                mechanic.getSpecialty(),
                mechanic.getWorkshopId(),
                mechanic.getActive()
        );
    }
}