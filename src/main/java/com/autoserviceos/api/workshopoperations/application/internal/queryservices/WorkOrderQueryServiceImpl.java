package com.autoserviceos.api.workshopoperations.application.internal.queryservices;

import com.autoserviceos.api.workshopoperations.application.queries.*;
import com.autoserviceos.api.workshopoperations.application.queryservices.WorkOrderQueryService;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.WorkOrder;
import com.autoserviceos.api.workshopoperations.domain.model.repositories.WorkOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation executing read-only database queries for work order tracking folders.
 */
@Service
@Transactional(readOnly = true)
public class WorkOrderQueryServiceImpl implements WorkOrderQueryService {
    private final WorkOrderRepository workOrderRepository;

    public WorkOrderQueryServiceImpl(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    public Optional<WorkOrder> handle(GetWorkOrderByIdQuery query) {
        return workOrderRepository.findById(query.id());
    }

    public List<WorkOrder> handle(GetAllWorkOrdersQuery query) {
        return workOrderRepository.findAll();
    }

    public List<WorkOrder> handle(GetWorkOrdersByWorkshopIdQuery query) {
        return workOrderRepository.findByWorkshopId(query.workshopId());
    }
}