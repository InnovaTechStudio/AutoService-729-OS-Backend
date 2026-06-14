package com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.repositories;

import com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.entities.WorkOrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataWorkOrderRepository extends JpaRepository<WorkOrderJpaEntity, Long> {
    List<WorkOrderJpaEntity> findByWorkshopId(String workshopId);
}