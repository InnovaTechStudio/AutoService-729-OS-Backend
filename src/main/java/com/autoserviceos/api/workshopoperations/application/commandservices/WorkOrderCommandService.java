package com.autoserviceos.api.workshopoperations.application.commandservices;

import com.autoserviceos.api.workshopoperations.application.commands.*;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.WorkOrder;
import java.util.Optional;

public interface WorkOrderCommandService {
    WorkOrder handle(CreateWorkOrderCommand command);
    Optional<WorkOrder> handle(UpdateWorkOrderCommand command);
    void handle(DeleteWorkOrderCommand command);
}