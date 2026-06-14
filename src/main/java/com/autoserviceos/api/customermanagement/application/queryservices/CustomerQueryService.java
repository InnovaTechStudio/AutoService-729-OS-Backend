package com.autoserviceos.api.customermanagement.application.queryservices;

import com.autoserviceos.api.customermanagement.domain.model.aggregates.Customer;
import com.autoserviceos.api.customermanagement.application.queries.GetAllCustomersByWorkshopIdQuery;
import com.autoserviceos.api.customermanagement.application.queries.GetCustomerByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Application service contract providing read access to customer data.
 */
public interface CustomerQueryService {
    Optional<Customer> handle(GetCustomerByIdQuery query);
    List<Customer> handle(GetAllCustomersByWorkshopIdQuery query);
}