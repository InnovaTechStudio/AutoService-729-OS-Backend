package com.autoserviceos.api.customermanagement.domain.model.repositories;

import com.autoserviceos.api.customermanagement.domain.model.aggregates.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository port for Customer aggregates.
 * The application layer depends on this abstraction, implemented by the infrastructure layer.
 */
public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAllByWorkshopId(String workshopId);
    void delete(Customer customer);
}