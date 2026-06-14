package com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa;

import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;
import com.autoserviceos.api.tenantmanagement.domain.model.repositories.WorkshopRepository;
import com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa.mappers.WorkshopPersistenceMapper;
import com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa.repositories.SpringDataWorkshopRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Persistence adapter providing implementation infrastructure mapping onto Domain Port specifications.
 */
@Component
public class WorkshopPersistenceAdapter implements WorkshopRepository {

    private final SpringDataWorkshopRepository repository;

    public WorkshopPersistenceAdapter(SpringDataWorkshopRepository repository) {
        this.repository = repository;
    }

    @Override
    public Workshop save(Workshop workshop) {
        var entity = WorkshopPersistenceMapper.toJpaEntity(workshop);
        var savedEntity = repository.save(entity);
        return WorkshopPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return repository.findById(id).map(WorkshopPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Workshop> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId).map(WorkshopPersistenceMapper::toDomain);
    }
}