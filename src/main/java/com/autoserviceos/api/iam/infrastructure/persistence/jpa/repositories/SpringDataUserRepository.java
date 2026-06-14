package com.autoserviceos.api.iam.infrastructure.persistence.jpa.repositories;

import com.autoserviceos.api.iam.infrastructure.persistence.jpa.entities.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Spring Data interface abstraction providing database access routines for User entities.
 */
public interface SpringDataUserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByEmail(String email);
}