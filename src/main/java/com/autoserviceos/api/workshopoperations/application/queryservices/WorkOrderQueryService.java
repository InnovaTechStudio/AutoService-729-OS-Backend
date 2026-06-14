package com.autoserviceos.api.workshopoperations.application.queryservices;

import com.autoserviceos.api.workshopoperations.application.queries.*;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.WorkOrder;
import java.util.List;
import java.util.Optional;

public interface WorkOrderQueryService {
    Optional<WorkOrder> handle(GetWorkOrderByIdQuery query);
    List<WorkOrder> handle(GetAllWorkOrdersQuery query);
    List<WorkOrder> handle(GetWorkOrdersByWorkshopIdQuery query);
}