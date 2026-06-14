package com.autoserviceos.api.tenantmanagement.infrastructure.persistence.jpa.entities;

import com.autoserviceos.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JPA entity representing the persistence structure for Workshops in the database.
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "workshops")
public class WorkshopJpaEntity extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String tenantId;

    public WorkshopJpaEntity(Long id, String name, String tenantId) {
        this.id = id;
        this.name = name;
        this.tenantId = tenantId;
    }
}