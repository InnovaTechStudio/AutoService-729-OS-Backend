package com.autoserviceos.api.customermanagement.interfaces.rest;

import com.autoserviceos.api.customermanagement.application.commandservices.CustomerCommandService;
import com.autoserviceos.api.customermanagement.application.queryservices.CustomerQueryService;
import com.autoserviceos.api.customermanagement.application.commands.DeleteCustomerCommand;
import com.autoserviceos.api.customermanagement.application.queries.GetAllCustomersByWorkshopIdQuery;
import com.autoserviceos.api.customermanagement.application.queries.GetCustomerByIdQuery;
import com.autoserviceos.api.customermanagement.interfaces.rest.resources.CreateCustomerResource;
import com.autoserviceos.api.customermanagement.interfaces.rest.resources.UpdateCustomerResource;
import com.autoserviceos.api.customermanagement.interfaces.rest.transform.CustomerResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Exposes RESTful endpoints for managing customers within the API.
 */
@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customers", description = "Endpoints for managing customers")
public class CustomersController {

    private final CustomerCommandService customerCommandService;
    private final CustomerQueryService customerQueryService;

    public CustomersController(CustomerCommandService customerCommandService, CustomerQueryService customerQueryService) {
        this.customerCommandService = customerCommandService;
        this.customerQueryService = customerQueryService;
    }

    /**
     * Temporary helper to simulate the authenticated user's Workshop ID.
     * To be replaced when IAM module is integrated.
     */
    private String getAuthenticatedWorkshopId() {
        return "WS-1";
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CreateCustomerResource resource) {
        var command = CustomerResourceAssembler.toCommandFromResource(resource, getAuthenticatedWorkshopId());
        var customer = customerCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResourceAssembler.toResourceFromEntity(customer));
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        var query = new GetAllCustomersByWorkshopIdQuery(getAuthenticatedWorkshopId());
        var customers = customerQueryService.handle(query);
        var resources = customers.stream().map(CustomerResourceAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        var query = new GetCustomerByIdQuery(id);
        var customer = customerQueryService.handle(query);
        if (customer.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CustomerResourceAssembler.toResourceFromEntity(customer.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody UpdateCustomerResource resource) {
        var command = CustomerResourceAssembler.toCommandFromResource(id, resource);
        var customer = customerCommandService.handle(command);
        if (customer.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CustomerResourceAssembler.toResourceFromEntity(customer.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        var command = new DeleteCustomerCommand(id);
        customerCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}