package com.autoserviceos.api.fleetmanagement.application.internal.queryservices;

import com.autoserviceos.api.fleetmanagement.application.queryservices.VehicleQueryService;
import com.autoserviceos.api.fleetmanagement.application.queries.*;
import com.autoserviceos.api.fleetmanagement.domain.model.aggregates.Vehicle;
import com.autoserviceos.api.fleetmanagement.domain.model.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/** Implementation executing read-only database queries for fleet information. */
@Service
@Transactional(readOnly = true)
public class VehicleQueryServiceImpl implements VehicleQueryService {
    private final VehicleRepository vehicleRepository;

    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.id());
    }

    @Override
    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }
}