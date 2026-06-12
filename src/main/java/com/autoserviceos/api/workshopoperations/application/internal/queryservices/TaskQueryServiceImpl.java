package com.autoserviceos.api.workshopoperations.application.internal.queryservices;

import com.autoserviceos.api.workshopoperations.application.queries.GetAllTasksQuery;
import com.autoserviceos.api.workshopoperations.application.queries.GetTaskByIdQuery;
import com.autoserviceos.api.workshopoperations.application.queries.GetTasksByMechanicIdQuery;
import com.autoserviceos.api.workshopoperations.application.queries.GetTasksByWorkOrderIdQuery;
import com.autoserviceos.api.workshopoperations.application.queryservices.TaskQueryService;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.Task;
import com.autoserviceos.api.workshopoperations.domain.model.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.id());
    }

    public List<Task> handle(GetAllTasksQuery query) {
        return taskRepository.findAll();
    }

    public List<Task> handle(GetTasksByWorkOrderIdQuery query) {
        return taskRepository.findByWorkOrderId(query.workOrderId());
    }

    public List<Task> handle(GetTasksByMechanicIdQuery query) {
        return taskRepository.findByMechanicId(query.mechanicId());
    }
}