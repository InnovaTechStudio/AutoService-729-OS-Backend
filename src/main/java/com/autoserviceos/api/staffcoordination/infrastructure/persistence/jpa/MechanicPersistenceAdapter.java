package com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa;

import com.autoserviceos.api.staffcoordination.domain.model.aggregates.Mechanic;
import com.autoserviceos.api.staffcoordination.domain.model.repositories.MechanicRepository;
import com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa.mappers.MechanicPersistenceMapper;
import com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa.repositories.SpringDataMechanicRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MechanicPersistenceAdapter implements MechanicRepository {
    private final SpringDataMechanicRepository repository;

    public MechanicPersistenceAdapter(SpringDataMechanicRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        var entity = MechanicPersistenceMapper.toJpaEntity(mechanic);
        return MechanicPersistenceMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Mechanic> findById(Long id) {
        return repository.findById(id).map(MechanicPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Mechanic> findByEmail(String email) {
        return repository.findByEmail(email).map(MechanicPersistenceMapper::toDomain);
    }

    @Override
    public List<Mechanic> findAllByWorkshopId(String workshopId) {
        return repository.findAllByWorkshopId(workshopId).stream()
                .map(MechanicPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Mechanic mechanic) {
        repository.deleteById(mechanic.getId());
    }
}