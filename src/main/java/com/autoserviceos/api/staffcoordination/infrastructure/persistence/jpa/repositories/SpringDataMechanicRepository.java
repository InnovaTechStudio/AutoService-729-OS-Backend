package com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa.repositories;
import com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa.entities.MechanicJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SpringDataMechanicRepository extends JpaRepository<MechanicJpaEntity, Long> {
    Optional<MechanicJpaEntity> findByEmail(String email);
    List<MechanicJpaEntity> findAllByWorkshopId(String workshopId);
}