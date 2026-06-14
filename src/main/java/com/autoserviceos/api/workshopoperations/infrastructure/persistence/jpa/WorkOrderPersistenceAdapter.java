package com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa;

import com.autoserviceos.api.workshopoperations.domain.model.aggregates.WorkOrder;
import com.autoserviceos.api.workshopoperations.domain.model.repositories.WorkOrderRepository;
import com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.mappers.WorkOrderPersistenceMapper;
import com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.repositories.SpringDataWorkOrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WorkOrderPersistenceAdapter implements WorkOrderRepository {
    private final SpringDataWorkOrderRepository repository;

    public WorkOrderPersistenceAdapter(SpringDataWorkOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public WorkOrder save(WorkOrder workOrder) {
        var entity = WorkOrderPersistenceMapper.toJpaEntity(workOrder);
        return WorkOrderPersistenceMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<WorkOrder> findById(Long id) {
        return repository.findById(id).map(WorkOrderPersistenceMapper::toDomain);
    }

    @Override
    public List<WorkOrder> findAll() {
        return repository.findAll().stream().map(WorkOrderPersistenceMapper::toDomain).toList();
    }

    @Override
    public List<WorkOrder> findByWorkshopId(String workshopId) {
        return repository.findByWorkshopId(workshopId).stream().map(WorkOrderPersistenceMapper::toDomain).toList();
    }

    @Override
    public void delete(WorkOrder workOrder) {
        repository.deleteById(workOrder.getId());
    }
}