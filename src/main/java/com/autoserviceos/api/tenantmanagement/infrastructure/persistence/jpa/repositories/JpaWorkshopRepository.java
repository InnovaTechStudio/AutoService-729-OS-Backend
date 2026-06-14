package com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa.repositories;

import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;
import com.autoserviceos.api.tenantmanagement.domain.repositories.WorkshopRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaWorkshopRepository extends JpaRepository<Workshop, Long>, WorkshopRepository {
}