package com.autoserviceos.api.staffcoordination.application.internal.commandservices;

import com.autoserviceos.api.iam.application.commands.SignUpCommand;
import com.autoserviceos.api.iam.application.commandservices.IamCommandService;
import com.autoserviceos.api.staffcoordination.application.commandservices.MechanicCommandService;
import com.autoserviceos.api.staffcoordination.application.commands.*;
import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import com.autoserviceos.api.staffcoordination.domain.model.repositories.MechanicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementation orchestrating mechanic staff coordination and cross-context IAM synchronization.
 */
@Service
public class MechanicCommandServiceImpl implements MechanicCommandService {

    private final MechanicRepository mechanicRepository;
    private final IamCommandService iamCommandService;

    public MechanicCommandServiceImpl(MechanicRepository mechanicRepository, IamCommandService iamCommandService) {
        this.mechanicRepository = mechanicRepository;
        this.iamCommandService = iamCommandService;
    }

    @Override
    @Transactional
    public Mechanic handle(CreateMechanicCommand command) {
        // 1. Cross-context sync: Create credentials first in the IAM context
        var signUpCommand = new SignUpCommand(command.email(), command.password(), "mechanic", command.workshopId());
        iamCommandService.handle(signUpCommand);

        // 2. Persist mechanic aggregate domain metrics data state
        var mechanic = Mechanic.create(command.fullName(), command.specialty(), command.maxCapacity(), command.email(), command.workshopId());
        return mechanicRepository.save(mechanic);
    }

    @Override
    @Transactional
    public Optional<Mechanic> handle(UpdateMechanicCommand command) {
        var result = mechanicRepository.findById(command.id());
        if (result.isEmpty()) return Optional.empty();

        var mechanic = result.get();
        mechanic.update(command.fullName(), command.specialty(), command.maxCapacity(), command.email());
        return Optional.of(mechanicRepository.save(mechanic));
    }

    @Override
    @Transactional
    public void handle(DeleteMechanicCommand command) {
        mechanicRepository.findById(command.id()).ifPresent(mechanicRepository::delete);
    }
}