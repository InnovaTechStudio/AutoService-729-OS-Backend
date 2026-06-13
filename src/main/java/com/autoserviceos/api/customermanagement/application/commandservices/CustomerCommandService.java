package com.autoserviceos.api.customermanagement.application.commandservices;

import com.autoserviceos.api.customermanagement.domain.model.aggregates.Customer;
import com.autoserviceos.api.customermanagement.application.commands.CreateCustomerCommand;
import com.autoserviceos.api.customermanagement.application.commands.DeleteCustomerCommand;
import com.autoserviceos.api.customermanagement.application.commands.UpdateCustomerCommand;

import java.util.Optional;

/**
 * Application service contract for coordinating customer command operations (write operations).
 */
public interface CustomerCommandService {
    Customer handle(CreateCustomerCommand command);
    Optional<Customer> handle(UpdateCustomerCommand command);
    void handle(DeleteCustomerCommand command);
}