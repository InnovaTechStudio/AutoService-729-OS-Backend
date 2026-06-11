package com.autoserviceos.api.staffcoordination.application.internal;

import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import com.autoserviceos.api.staffcoordination.domain.repositories.MechanicRepository;
import com.autoserviceos.api.staffcoordination.interfaces.rest.resources.CreateMechanicResource;
import org.springframework.stereotype.Service;

@Service
public class MechanicCommandService {

    private final MechanicRepository mechanicRepository;

    public MechanicCommandService(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    public Mechanic createMechanic(CreateMechanicResource resource) {
        var mechanic = new Mechanic(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phone(),
                resource.specialty(),
                resource.workshopId()
        );

        return mechanicRepository.save(mechanic);
    }
}