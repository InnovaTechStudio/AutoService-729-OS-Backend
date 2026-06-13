package com.autoserviceos.api.customermanagement.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Database entity mapped to the "customers" table using JPA.
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String workshopId;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, length = 20)
    private String dni;

    @Column(length = 100)
    private String email;

    @Column(length = 30)
    private String phone;

    public CustomerJpaEntity(Long id, String workshopId, String fullName, String dni, String email, String phone) {
        this.id = id;
        this.workshopId = workshopId;
        this.fullName = fullName;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
    }
}