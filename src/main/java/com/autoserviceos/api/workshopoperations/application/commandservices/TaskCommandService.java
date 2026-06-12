package com.autoserviceos.api.workshopoperations.application.commandservices;

import com.autoserviceos.api.workshopoperations.application.commands.CreateTaskCommand;
import com.autoserviceos.api.workshopoperations.application.commands.DeleteTaskCommand;
import com.autoserviceos.api.workshopoperations.application.commands.PatchTaskCommand;
import com.autoserviceos.api.workshopoperations.application.commands.UpdateTaskCommand;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.Task;

import java.util.Optional;

public interface TaskCommandService {
    Task handle(CreateTaskCommand command);
    Optional<Task> handle(UpdateTaskCommand command);
    Optional<Task> handle(PatchTaskCommand command);
    void handle(DeleteTaskCommand command);
}
