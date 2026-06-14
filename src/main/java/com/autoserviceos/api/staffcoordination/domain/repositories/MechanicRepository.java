package com.autoserviceos.api.staffcoordination.domain.repositories;

import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;

import java.util.List;
import java.util.Optional;

public interface MechanicRepository {
    List<Mechanic> findAll();
    Optional<Mechanic> findById(Long id);
    Mechanic save(Mechanic mechanic);
}