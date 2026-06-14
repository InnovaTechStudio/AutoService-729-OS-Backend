package com.autoserviceos.api.staffcoordination.infrastructure.persistence.jpa.entities;

import com.autoserviceos.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** JPA entity representing the technical staff profile data store table. */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "mechanics")
public class MechanicJpaEntity extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String specialty;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private Integer maxCapacity;

    @Column(nullable = false, length = 50)
    private String workshopId;

    public MechanicJpaEntity(Long id, String fullName, String specialty, Integer maxCapacity, String email, String workshopId) {
        this.id = id;
        this.fullName = fullName;
        this.specialty = specialty;
        this.maxCapacity = maxCapacity;
        this.email = email;
        this.workshopId = workshopId;
    }
}