package com.autoserviceos.api.fleetmanagement.infrastructure.persistence.jpa.repositories;
import com.autoserviceos.api.fleetmanagement.infrastructure.persistence.jpa.entities.VehicleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SpringDataVehicleRepository extends JpaRepository<VehicleJpaEntity, Long> {}