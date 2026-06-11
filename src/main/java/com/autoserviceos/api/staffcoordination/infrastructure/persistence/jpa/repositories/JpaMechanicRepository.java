package com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa.repositories;

import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import com.autoserviceos.api.staffcoordination.domain.repositories.MechanicRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMechanicRepository extends JpaRepository<Mechanic, Long>, MechanicRepository {
}