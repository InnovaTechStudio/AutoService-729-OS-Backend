package com.autoserviceos.api.tenantmanagement.application.internal;

import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;
import com.autoserviceos.api.tenantmanagement.domain.repositories.WorkshopRepository;
import com.autoserviceos.api.tenantmanagement.interfaces.rest.resources.CreateWorkshopResource;
import org.springframework.stereotype.Service;

@Service
public class WorkshopCommandService {

    private final WorkshopRepository workshopRepository;

    public WorkshopCommandService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public Workshop createWorkshop(CreateWorkshopResource resource) {
        var workshop = new Workshop(
                resource.name(),
                resource.ruc(),
                resource.address(),
                resource.phone(),
                resource.email()
        );

        return workshopRepository.save(workshop);
    }
}