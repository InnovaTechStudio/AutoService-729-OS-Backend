package com.autoserviceos.api.staffcoordination.application.queryservices;

import com.autoserviceos.api.staffcoordination.application.queries.*;
import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import java.util.List;
import java.util.Optional;

public interface MechanicQueryService {
    Optional<Mechanic> handle(GetMechanicByIdQuery query);
    List<Mechanic> handle(GetAllMechanicsByWorkshopIdQuery query);
}