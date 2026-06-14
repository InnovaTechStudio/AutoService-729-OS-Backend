package com.autoserviceos.api.tenantmanagement.application.internal.commandservices;

import com.autoserviceos.api.tenantmanagement.application.commands.CreateWorkshopCommand;
import com.autoserviceos.api.tenantmanagement.application.commandservices.WorkshopCommandService;
import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;
import com.autoserviceos.api.tenantmanagement.domain.model.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Internal application service coordinating the physical creation routine steps
 * of a multi-tenant workshop architecture model.
 */
@Service
public class WorkshopCommandServiceImpl implements WorkshopCommandService {

    private final WorkshopRepository workshopRepository;

    public WorkshopCommandServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Override
    @Transactional
    public Optional<Workshop> handle(CreateWorkshopCommand command) {
        var workshop = Workshop.create(command.workshopName());
        var savedWorkshop = workshopRepository.save(workshop);
        return Optional.of(savedWorkshop);
    }
}