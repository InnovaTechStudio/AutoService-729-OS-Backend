package com.autoserviceos.api.customermanagement.application.internal.commandservices;

import com.autoserviceos.api.customermanagement.application.commandservices.CustomerCommandService;
import com.autoserviceos.api.customermanagement.application.commands.CreateCustomerCommand;
import com.autoserviceos.api.customermanagement.application.commands.DeleteCustomerCommand;
import com.autoserviceos.api.customermanagement.application.commands.UpdateCustomerCommand;
import com.autoserviceos.api.customermanagement.domain.model.aggregates.Customer;
import com.autoserviceos.api.customermanagement.domain.model.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementation of the CustomerCommandService.
 * Coordinates creation, updates, and deletion of customers.
 */
@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {
    private final CustomerRepository customerRepository;

    public CustomerCommandServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer handle(CreateCustomerCommand command) {
        var customer = Customer.create(
                command.workshopId(),
                command.fullName(),
                command.dni(),
                command.email(),
                command.phone()
        );
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Optional<Customer> handle(UpdateCustomerCommand command) {
        var result = customerRepository.findById(command.id());
        if (result.isEmpty()) return Optional.empty();

        var customer = result.get();
        customer.update(command.fullName(), command.dni(), command.email(), command.phone());
        return Optional.of(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public void handle(DeleteCustomerCommand command) {
        customerRepository.findById(command.id()).ifPresent(customerRepository::delete);
    }
}