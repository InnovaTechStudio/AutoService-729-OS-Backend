package com.autoserviceos.api.fleetmanagement.infrastructure.persistence.jpa;

import com.autoserviceos.api.fleetmanagement.domain.model.aggregates.Vehicle;
import com.autoserviceos.api.fleetmanagement.domain.model.repositories.VehicleRepository;
import com.autoserviceos.api.fleetmanagement.infrastructure.persistence.jpa.mappers.VehiclePersistenceMapper;
import com.autoserviceos.api.fleetmanagement.infrastructure.persistence.jpa.repositories.SpringDataVehicleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehiclePersistenceAdapter implements VehicleRepository {
    private final SpringDataVehicleRepository repository;

    public VehiclePersistenceAdapter(SpringDataVehicleRepository repository) { this.repository = repository; }

    @Override
    public Vehicle save(Vehicle vehicle) {
        var entity = VehiclePersistenceMapper.toJpaEntity(vehicle);
        return VehiclePersistenceMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return repository.findById(id).map(VehiclePersistenceMapper::toDomain);
    }

    @Override
    public List<Vehicle> findAll() {
        return repository.findAll().stream().map(VehiclePersistenceMapper::toDomain).toList();
    }

    @Override
    public void delete(Vehicle vehicle) {
        repository.deleteById(vehicle.getId());
    }
}