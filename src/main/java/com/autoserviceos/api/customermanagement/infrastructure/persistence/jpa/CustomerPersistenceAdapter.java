package com.autoserviceos.api.customermanagement.infrastructure.persistence.jpa;

import com.autoserviceos.api.customermanagement.domain.model.aggregates.Customer;
import com.autoserviceos.api.customermanagement.domain.model.repositories.CustomerRepository;
import com.autoserviceos.api.customermanagement.infrastructure.persistence.jpa.mappers.CustomerPersistenceMapper;
import com.autoserviceos.api.customermanagement.infrastructure.persistence.jpa.repositories.SpringDataCustomerJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Infrastructure adapter that implements the domain CustomerRepository interface.
 * Connects the domain logic to the underlying Spring Data JPA repository.
 */
@Component
public class CustomerPersistenceAdapter implements CustomerRepository {
    private final SpringDataCustomerJpaRepository repository;

    public CustomerPersistenceAdapter(SpringDataCustomerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        var entity = CustomerPersistenceMapper.toJpaEntity(customer);
        var savedEntity = repository.save(entity);
        return CustomerPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id).map(CustomerPersistenceMapper::toDomain);
    }

    @Override
    public List<Customer> findAllByWorkshopId(String workshopId) {
        return repository.findAllByWorkshopId(workshopId).stream()
                .map(CustomerPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Customer customer) {
        repository.deleteById(customer.getId());
    }
}