package com.autoserviceos.api.customermanagement.infrastructure.persistence.jpa.repositories;

import com.autoserviceos.api.customermanagement.infrastructure.persistence.jpa.entities.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Spring Data JPA repository for executing database operations on CustomerJpaEntity.
 */
public interface SpringDataCustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long> {
    List<CustomerJpaEntity> findAllByWorkshopId(String workshopId);
}