package com.autoserviceos.api.shared.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Base class for all JPA entities that require auditing.
 * Automatically populates the createdAt and updatedAt fields when an entity is persisted or updated.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableModel {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}