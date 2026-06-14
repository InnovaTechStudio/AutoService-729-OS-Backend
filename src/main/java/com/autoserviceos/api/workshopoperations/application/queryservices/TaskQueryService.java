package com.autoserviceos.api.workshopoperations.application.queryservices;

import com.autoserviceos.api.workshopoperations.application.queries.*;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.Task;
import java.util.List;
import java.util.Optional;

public interface TaskQueryService {
    Optional<Task> handle(GetTaskByIdQuery query);
    List<Task> handle(GetAllTasksQuery query);
    List<Task> handle(GetTasksByWorkOrderIdQuery query);
    List<Task> handle(GetTasksByMechanicIdQuery query);
}