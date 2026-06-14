package com.autoserviceos.api.iam.infrastructure.persistence.jpa.mappers;

import com.autoserviceos.api.iam.domain.model.aggregates.User;
import com.autoserviceos.api.iam.infrastructure.persistence.jpa.entities.UserJpaEntity;

/**
 * Bidirectional component mapper mapping values between User Aggregate and UserJpaEntity.
 */
public final class UserPersistenceMapper {

    private UserPersistenceMapper() {}

    public static UserJpaEntity toJpaEntity(User aggregate) {
        return new UserJpaEntity(
                aggregate.getId(), aggregate.getEmail(), aggregate.getPasswordHash(),
                aggregate.getRole(), aggregate.getWorkshopId()
        );
    }

    public static User toDomain(UserJpaEntity entity) {
        return User.rehydrate(
                entity.getId(), entity.getEmail(), entity.getPasswordHash(),
                entity.getRole(), entity.getWorkshopId()
        );
    }
}