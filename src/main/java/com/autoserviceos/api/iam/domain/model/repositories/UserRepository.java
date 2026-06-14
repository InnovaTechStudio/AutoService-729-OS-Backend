package com.autoserviceos.api.iam.domain.model.repositories;

import com.autoserviceos.api.iam.domain.model.aggregates.User;
import java.util.Optional;

/**
 * Domain repository port interface for managing User aggregates.
 * Decouples domain operations from persistence mechanisms.
 */
public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}