package com.autoserviceos.api.staffcoordination.application.commandservices;

import com.autoserviceos.api.staffcoordination.application.commands.*;
import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import java.util.Optional;

public interface MechanicCommandService {
    Mechanic handle(CreateMechanicCommand command);
    Optional<Mechanic> handle(UpdateMechanicCommand command);
    void handle(DeleteMechanicCommand command);
}