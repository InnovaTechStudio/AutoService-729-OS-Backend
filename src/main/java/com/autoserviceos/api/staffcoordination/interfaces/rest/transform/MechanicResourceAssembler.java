package com.autoserviceos.api.staffcoordination.interfaces.rest.transform;

import com.autoserviceos.api.staffcoordination.application.commands.CreateMechanicCommand;
import com.autoserviceos.api.staffcoordination.application.commands.UpdateMechanicCommand;
import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import com.autoserviceos.api.staffcoordination.interfaces.rest.resources.CreateMechanicResource;
import com.autoserviceos.api.staffcoordination.interfaces.rest.resources.MechanicResource;
import com.autoserviceos.api.staffcoordination.interfaces.rest.resources.UpdateMechanicResource;

public class MechanicResourceAssembler {
    public static MechanicResource toResourceFromEntity(Mechanic entity) {
        return new MechanicResource(entity.getId(), entity.getFullName(), entity.getSpecialty(),
                entity.getMaxCapacity(), entity.getEmail(), entity.getWorkshopId());
    }

    public static CreateMechanicCommand toCommandFromResource(CreateMechanicResource resource, String workshopId) {
        return new CreateMechanicCommand(resource.fullName(), resource.specialty(), resource.maxCapacity(),
                resource.email(), resource.password(), workshopId);
    }

    public static UpdateMechanicCommand toCommandFromResource(Long id, UpdateMechanicResource resource) {
        return new UpdateMechanicCommand(id, resource.fullName(), resource.specialty(), resource.maxCapacity(), resource.email());
    }
}