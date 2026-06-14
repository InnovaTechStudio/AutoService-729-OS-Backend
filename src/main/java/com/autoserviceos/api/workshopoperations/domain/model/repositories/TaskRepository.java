package com.autoserviceos.api.workshopoperations.domain.model.repositories;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    List<Task> findByWorkOrderId(Long workOrderId);
    List<Task> findByMechanicId(Long mechanicId);
    void delete(Task task);
}