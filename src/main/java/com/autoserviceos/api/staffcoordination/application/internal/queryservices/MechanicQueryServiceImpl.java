package com.autoserviceos.api.staffcoordination.application.internal.queryservices;

import com.autoserviceos.api.staffcoordination.application.queryservices.MechanicQueryService;
import com.autoserviceos.api.staffcoordination.application.queries.*;
import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import com.autoserviceos.api.staffcoordination.domain.model.repositories.MechanicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MechanicQueryServiceImpl implements MechanicQueryService {

    private final MechanicRepository mechanicRepository;

    public MechanicQueryServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Optional<Mechanic> handle(GetMechanicByIdQuery query) {
        return mechanicRepository.findById(query.id());
    }

    @Override
    public List<Mechanic> handle(GetAllMechanicsByWorkshopIdQuery query) {
        return mechanicRepository.findAllByWorkshopId(query.workshopId());
    }
}