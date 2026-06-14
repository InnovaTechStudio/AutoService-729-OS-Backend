package com.autoserviceos.api.customermanagement.application.internal.queryservices;

import com.autoserviceos.api.customermanagement.application.queryservices.CustomerQueryService;
import com.autoserviceos.api.customermanagement.application.queries.GetAllCustomersByWorkshopIdQuery;
import com.autoserviceos.api.customermanagement.application.queries.GetCustomerByIdQuery;
import com.autoserviceos.api.customermanagement.domain.model.aggregates.Customer;
import com.autoserviceos.api.customermanagement.domain.model.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CustomerQueryService.
 * Translates domain queries into repository operations.
 */
@Service
@Transactional(readOnly = true)
public class CustomerQueryServiceImpl implements CustomerQueryService {
    private final CustomerRepository customerRepository;

    public CustomerQueryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> handle(GetCustomerByIdQuery query) {
        return customerRepository.findById(query.id());
    }

    @Override
    public List<Customer> handle(GetAllCustomersByWorkshopIdQuery query) {
        return customerRepository.findAllByWorkshopId(query.workshopId());
    }
}