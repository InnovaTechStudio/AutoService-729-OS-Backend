package com.autoserviceos.api.fleetmanagement.application.internal.commandservices;

import com.autoserviceos.api.fleetmanagement.application.commandservices.VehicleCommandService;
import com.autoserviceos.api.fleetmanagement.application.commands.*;
import com.autoserviceos.api.fleetmanagement.domain.model.aggregates.Vehicle;
import com.autoserviceos.api.fleetmanagement.domain.model.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/** Implementation orchestrating vehicle creation, updates, and deletions. */
@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {
    private final VehicleRepository vehicleRepository;

    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional
    public Vehicle handle(CreateVehicleCommand command) {
        var vehicle = Vehicle.create(command.plate(), command.brand(), command.model(), command.year(),
                command.color(), command.status(), command.image(), command.customerId());
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var result = vehicleRepository.findById(command.id());
        if (result.isEmpty()) return Optional.empty();

        var vehicle = result.get();
        vehicle.update(command.plate(), command.brand(), command.model(), command.year(),
                command.color(), command.status(), command.image(), command.customerId());
        return Optional.of(vehicleRepository.save(vehicle));
    }

    @Override
    @Transactional
    public void handle(DeleteVehicleCommand command) {
        vehicleRepository.findById(command.id()).ifPresent(vehicleRepository::delete);
    }
}