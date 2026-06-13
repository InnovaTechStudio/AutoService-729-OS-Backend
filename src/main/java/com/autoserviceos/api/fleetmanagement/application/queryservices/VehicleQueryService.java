package com.autoserviceos.api.fleetmanagement.application.queryservices;

import com.autoserviceos.api.fleetmanagement.application.queries.*;
import com.autoserviceos.api.fleetmanagement.domain.model.aggregates.Vehicle;
import java.util.List;
import java.util.Optional;

/** Service contract for managing vehicle domain read operations. */
public interface VehicleQueryService {
    Optional<Vehicle> handle(GetVehicleByIdQuery query);
    List<Vehicle> handle(GetAllVehiclesQuery query);
}