package com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.repositories;

import com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.entities.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataTaskRepository extends JpaRepository<TaskJpaEntity, Long> {
    List<TaskJpaEntity> findByWorkOrderId(Long workOrderId);
    List<TaskJpaEntity> findByMechanicId(Long mechanicId);
}