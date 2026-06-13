package com.autoserviceos.api.fleetmanagement.domain.model.repositories;

import com.autoserviceos.api.fleetmanagement.domain.model.aggregates.Vehicle;
import java.util.List;
import java.util.Optional;

/**
 * Domain repository port defining the persistence contract for managing Vehicle entities.
 */
public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    List<Vehicle> findAll();
    void delete(Vehicle vehicle);
}