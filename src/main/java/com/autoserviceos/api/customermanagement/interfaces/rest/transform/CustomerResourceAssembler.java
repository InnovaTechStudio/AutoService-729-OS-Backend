package com.autoserviceos.api.customermanagement.interfaces.rest.transform;

import com.autoserviceos.api.customermanagement.application.commands.CreateCustomerCommand;
import com.autoserviceos.api.customermanagement.application.commands.UpdateCustomerCommand;
import com.autoserviceos.api.customermanagement.domain.model.aggregates.Customer;
import com.autoserviceos.api.customermanagement.interfaces.rest.resources.CreateCustomerResource;
import com.autoserviceos.api.customermanagement.interfaces.rest.resources.CustomerResource;
import com.autoserviceos.api.customermanagement.interfaces.rest.resources.UpdateCustomerResource;

/**
 * Translator class for converting between HTTP REST resources and Domain commands/entities.
 */
public class CustomerResourceAssembler {

    /** Converts a Customer entity to a response resource. */
    public static CustomerResource toResourceFromEntity(Customer entity) {
        return new CustomerResource(
                entity.getId(),
                entity.getWorkshopId(),
                entity.getFullName(),
                entity.getDni(),
                entity.getEmail(),
                entity.getPhone()
        );
    }

    /** Converts a creation request to a domain command. */
    public static CreateCustomerCommand toCommandFromResource(CreateCustomerResource resource, String workshopId) {
        return new CreateCustomerCommand(workshopId, resource.fullName(), resource.dni(), resource.email(), resource.phone());
    }

    /** Converts an update request to a domain command. */
    public static UpdateCustomerCommand toCommandFromResource(Long id, UpdateCustomerResource resource) {
        return new UpdateCustomerCommand(id, resource.fullName(), resource.dni(), resource.email(), resource.phone());
    }
}