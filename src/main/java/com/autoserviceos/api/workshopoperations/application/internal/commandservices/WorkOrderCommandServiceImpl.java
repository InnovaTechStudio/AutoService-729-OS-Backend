package com.autoserviceos.api.workshopoperations.application.internal.commandservices;

import com.autoserviceos.api.workshopoperations.application.commands.*;
import com.autoserviceos.api.workshopoperations.application.commandservices.WorkOrderCommandService;
import com.autoserviceos.api.workshopoperations.domain.model.aggregates.WorkOrder;
import com.autoserviceos.api.workshopoperations.domain.model.repositories.WorkOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WorkOrderCommandServiceImpl implements WorkOrderCommandService {
    private final WorkOrderRepository repository;
    public WorkOrderCommandServiceImpl(WorkOrderRepository repository) { this.repository = repository; }

    @Transactional
    public WorkOrder handle(CreateWorkOrderCommand command) {
        var workOrder = WorkOrder.create(command.workshopId(), command.vehicleId(), command.customerId(), command.mechanicId(), command.description(), command.estimatedDate(), BigDecimal.ZERO);
        return repository.save(workOrder);
    }

    @Transactional
    public Optional<WorkOrder> handle(UpdateWorkOrderCommand command) {
        var result = repository.findById(command.id());
        if (result.isEmpty()) return Optional.empty();
        var workOrder = result.get();
        workOrder.update(command.description(), command.estimatedDate(), command.price());
        workOrder.updateChecklist(command.tasksCompleted(), command.sparePartsChecked(), command.diagnosisValidated(), command.cleaningDone(), command.finalTestDone());
        workOrder.updateStatus(command.status());
        return Optional.of(repository.save(workOrder));
    }

    @Transactional
    public void handle(DeleteWorkOrderCommand command) {
        repository.findById(command.id()).ifPresent(repository::delete);
    }
}