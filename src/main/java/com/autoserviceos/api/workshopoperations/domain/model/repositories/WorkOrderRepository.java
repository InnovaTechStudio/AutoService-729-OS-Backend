package com.autoserviceos.api.workshopoperations.domain.model.repositories;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.WorkOrder;
import java.util.List;
import java.util.Optional;

public interface WorkOrderRepository {
    WorkOrder save(WorkOrder workOrder);
    Optional<WorkOrder> findById(Long id);
    List<WorkOrder> findAll();
    List<WorkOrder> findByWorkshopId(String workshopId);
    void delete(WorkOrder workOrder);
}