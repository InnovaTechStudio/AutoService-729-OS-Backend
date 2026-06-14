package com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa;

import com.autoserviceos.api.workshopoperations.domain.model.aggregates.Task;
import com.autoserviceos.api.workshopoperations.domain.model.repositories.TaskRepository;
import com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.mappers.TaskPersistenceMapper;
import com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.repositories.SpringDataTaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskPersistenceAdapter implements TaskRepository {
    private final SpringDataTaskRepository repository;

    public TaskPersistenceAdapter(SpringDataTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task save(Task task) {
        var entity = TaskPersistenceMapper.toJpaEntity(task);
        return TaskPersistenceMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id).map(TaskPersistenceMapper::toDomain);
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll().stream().map(TaskPersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Task> findByWorkOrderId(Long workOrderId) {
        return repository.findByWorkOrderId(workOrderId).stream().map(TaskPersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Task> findByMechanicId(Long mechanicId) {
        return repository.findByMechanicId(mechanicId).stream().map(TaskPersistenceMapper::toDomain).toList();
    }

    @Override
    public void delete(Task task) {
        repository.deleteById(task.getId());
    }
}