package com.autoserviceos.api.fleetmanagement.application.commandservices;

import com.autoserviceos.api.fleetmanagement.application.commands.*;
import com.autoserviceos.api.fleetmanagement.domain.model.aggregates.Vehicle;
import java.util.Optional;

/** Service contract for managing vehicle domain write operations. */
public interface VehicleCommandService {
    Vehicle handle(CreateVehicleCommand command);
    Optional<Vehicle> handle(UpdateVehicleCommand command);
    void handle(DeleteVehicleCommand command);
}