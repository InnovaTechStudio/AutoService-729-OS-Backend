package com.autoserviceos.api.workshopoperations.application.internal.commandservices;

import com.autoserviceos.api.workshopoperations.application.commands.CreateTaskCommand;
import com.autoserviceos.api.workshopoperations.application.commands.DeleteTaskCommand;
import com.autoserviceos.api.workshopoperations.application.commands.PatchTaskCommand;
import com.autoserviceos.api.workshopoperations.application.commands.UpdateTaskCommand;
import com.autoserviceos.api.workshopoperations.application.commandservices.TaskCommandService;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.Task;
import com.autoserviceos.api.workshopoperations.domain.model.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {
    private final TaskRepository taskRepository;

    public TaskCommandServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task handle(CreateTaskCommand command) {
        var task = Task.create(command.workOrderId(), command.mechanicId(), command.description(),
                command.priority(), command.estimatedTime(), command.laborPrice());
        return taskRepository.save(task);
    }

    @Transactional
    public Optional<Task> handle(UpdateTaskCommand command) {
        var result = taskRepository.findById(command.id());
        if (result.isEmpty()) return Optional.empty();

        var task = result.get();
        task.update(command.description(), command.status(), command.priority(),
                command.estimatedTime(), command.laborPrice(), command.mechanicId());
        return Optional.of(taskRepository.save(task));
    }

    @Transactional
    public Optional<Task> handle(PatchTaskCommand command) {
        var result = taskRepository.findById(command.id());
        if (result.isEmpty()) return Optional.empty();

        var task = result.get();
        task.patchTechnicalData(command.status(), command.technicalDiagnosis(), command.customerExplanation(),
                command.internalObservation(), command.evidenceRegistered(), command.adminReviewStatus());
        return Optional.of(taskRepository.save(task));
    }

    @Transactional
    public void handle(DeleteTaskCommand command) {
        taskRepository.findById(command.id()).ifPresent(taskRepository::delete);
    }
}
