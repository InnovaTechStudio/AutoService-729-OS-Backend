package com.autoserviceos.api.workshopoperations.application.queryservices;

import com.autoserviceos.api.workshopoperations.application.queries.GetAllTasksQuery;
import com.autoserviceos.api.workshopoperations.application.queries.GetTaskByIdQuery;
import com.autoserviceos.api.workshopoperations.application.queries.GetTasksByMechanicIdQuery;
import com.autoserviceos.api.workshopoperations.application.queries.GetTasksByWorkOrderIdQuery;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.Task;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {
    Optional<Task> handle(GetTaskByIdQuery query);
    List<Task> handle(GetAllTasksQuery query);
    List<Task> handle(GetTasksByWorkOrderIdQuery query);
    List<Task> handle(GetTasksByMechanicIdQuery query);
}
