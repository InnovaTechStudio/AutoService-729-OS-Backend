package com.autoserviceos.api.publictracking.interfaces.rest;

import com.autoserviceos.api.customermanagement.application.queries.GetCustomerByIdQuery;
import com.autoserviceos.api.customermanagement.application.queryservices.CustomerQueryService;
import com.autoserviceos.api.fleetmanagement.application.queries.GetVehicleByIdQuery;
import com.autoserviceos.api.fleetmanagement.application.queryservices.VehicleQueryService;
import com.autoserviceos.api.fleetmanagement.interfaces.rest.resources.VehicleResource;
import com.autoserviceos.api.fleetmanagement.interfaces.rest.transform.VehicleResourceAssembler;
import com.autoserviceos.api.publictracking.interfaces.rest.resources.TrackingCustomerResource;
import com.autoserviceos.api.publictracking.interfaces.rest.resources.TrackingWorkshopResource;
import com.autoserviceos.api.tenantmanagement.domain.model.repositories.WorkshopRepository;

// TODO: Import these once WorkshopOperations context is migrated
// import com.autoserviceos.api.workshopoperations.application.queries.GetAllWorkOrdersQuery;
// import com.autoserviceos.api.workshopoperations.application.queries.GetTasksByWorkOrderIdQuery;
// import com.autoserviceos.api.workshopoperations.application.queryservices.WorkOrderQueryService;
// import com.autoserviceos.api.workshopoperations.application.queryservices.TaskQueryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Exposes public RESTful endpoints for customers to track their vehicle's repair status
 * without requiring system authentication.
 */
@RestController
@RequestMapping("/api/v1/tracking")
@Tag(name = "Public Tracking", description = "Public endpoints for order and vehicle status tracking")
public class TrackingController {

    private final VehicleQueryService vehicleQueryService;
    private final CustomerQueryService customerQueryService;
    private final WorkshopRepository workshopRepository;

    // private final WorkOrderQueryService workOrderQueryService;
    // private final TaskQueryService taskQueryService;

    public TrackingController(
            VehicleQueryService vehicleQueryService,
            CustomerQueryService customerQueryService,
            WorkshopRepository workshopRepository
            /*, WorkOrderQueryService workOrderQueryService, TaskQueryService taskQueryService */) {

        this.vehicleQueryService = vehicleQueryService;
        this.customerQueryService = customerQueryService;
        this.workshopRepository = workshopRepository;
        // this.workOrderQueryService = workOrderQueryService;
        // this.taskQueryService = taskQueryService;
    }

    @GetMapping("/orders/{trackingCode}")
    public ResponseEntity<?> getOrderByTrackingCode(@PathVariable String trackingCode) {
        // TODO: Uncomment and implement when WorkshopOperations is migrated
        /*
        var orders = workOrderQueryService.handle(new GetAllWorkOrdersQuery());
        var matchedOrder = orders.stream()
                .filter(o -> trackingCode.equals(o.getTrackingCode()))
                .findFirst();

        if (matchedOrder.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(List.of(matchedOrder.get()));
        */
        return ResponseEntity.status(501).body("Not Implemented Yet - Pending WorkshopOperations Module");
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<?> getVehicle(@PathVariable Long id) {
        var vehicle = vehicleQueryService.handle(new GetVehicleByIdQuery(id));
        if (vehicle.isEmpty()) return ResponseEntity.notFound().build();

        VehicleResource resource = VehicleResourceAssembler.toResourceFromEntity(vehicle.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getTasksByOrder(@RequestParam Long workOrderId) {
        // TODO: Uncomment and implement when WorkshopOperations is migrated
        /*
        var tasks = taskQueryService.handle(new GetTasksByWorkOrderIdQuery(workOrderId));
        return ResponseEntity.ok(tasks);
        */
        return ResponseEntity.status(501).body("Not Implemented Yet - Pending WorkshopOperations Module");
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        var customer = customerQueryService.handle(new GetCustomerByIdQuery(id));
        if (customer.isEmpty()) return ResponseEntity.notFound().build();

        var resource = new TrackingCustomerResource(customer.get().getId(), customer.get().getFullName());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/workshops/{id}")
    public ResponseEntity<?> getWorkshop(@PathVariable String id) {
        // Here 'id' corresponds to the generated TenantId (e.g. WS-XXXX)
        var workshop = workshopRepository.findByTenantId(id);
        if (workshop.isEmpty()) return ResponseEntity.notFound().build();

        var resource = new TrackingWorkshopResource(workshop.get().getId(), workshop.get().getName());
        return ResponseEntity.ok(resource);
    }
}