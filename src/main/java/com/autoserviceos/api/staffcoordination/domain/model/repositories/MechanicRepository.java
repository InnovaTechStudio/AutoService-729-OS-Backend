package com.autoserviceos.api.staffcoordination.domain.model.repositories;

import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import java.util.List;
import java.util.Optional;

/**
 * Domain repository port defining the persistence contract for managing Mechanic entities.
 */
public interface MechanicRepository {
    Mechanic save(Mechanic mechanic);
    Optional<Mechanic> findById(Long id);
    Optional<Mechanic> findByEmail(String email);
    List<Mechanic> findAllByWorkshopId(String workshopId);
    void delete(Mechanic mechanic);
}