package com.autoserviceos.api.fleetmanagement.infrastructure.persistence.jpa.entities;

import com.autoserviceos.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** JPA entity mapped to the vehicles table in the database. */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "vehicles")
public class VehicleJpaEntity extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String plate;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false, length = 4)
    private String year;

    @Column(length = 30)
    private String color;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(length = 255)
    private String image;

    @Column(nullable = false)
    private Long customerId;

    public VehicleJpaEntity(Long id, String plate, String brand, String model, String year, String color, String status, String image, Long customerId) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.status = status;
        this.image = image;
        this.customerId = customerId;
    }
}