package com.autoserviceos.api.iam.infrastructure.persistence.jpa;

import com.autoserviceos.api.iam.domain.model.aggregates.User;
import com.autoserviceos.api.iam.domain.model.repositories.UserRepository;
import com.autoserviceos.api.iam.infrastructure.persistence.jpa.mappers.UserPersistenceMapper;
import com.autoserviceos.api.iam.infrastructure.persistence.jpa.repositories.SpringDataUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Persistence adapter providing implementation infrastructure mapping onto Domain Port specifications.
 */
@Component
public class UserPersistenceAdapter implements UserRepository {

    private final SpringDataUserRepository repository;

    public UserPersistenceAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        var entity = UserPersistenceMapper.toJpaEntity(user);
        var savedEntity = repository.save(entity);
        return UserPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(UserPersistenceMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(UserPersistenceMapper::toDomain);
    }
}