package com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.entities;
import com.autoserviceos.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "work_orders")
public class WorkOrderJpaEntity extends AuditableModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50) private String workshopId;
    @Column(nullable = false, unique = true, length = 20) private String trackingCode;
    @Column(nullable = false) private Long vehicleId;
    @Column(nullable = false) private Long customerId;
    @Column(nullable = false) private Long mechanicId;
    @Column(columnDefinition = "TEXT") private String description;
    @Column(length = 50) private String status;
    @Column(precision = 10, scale = 2) private BigDecimal price;
    @Column(length = 30) private String estimatedDate;
    @Column(length = 30) private String startDate;
    private Boolean tasksCompleted;
    private Boolean sparePartsChecked;
    private Boolean diagnosisValidated;
    private Boolean cleaningDone;
    private Boolean finalTestDone;

    public WorkOrderJpaEntity(Long id, String workshopId, String trackingCode, Long vehicleId, Long customerId, Long mechanicId, String description, String status, BigDecimal price, String estimatedDate, String startDate, Boolean tasksCompleted, Boolean sparePartsChecked, Boolean diagnosisValidated, Boolean cleaningDone, Boolean finalTestDone) {
        this.id = id;
        this.workshopId = workshopId;
        this.trackingCode = trackingCode;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.mechanicId = mechanicId;
        this.description = description;
        this.status = status;
        this.price = price;
        this.estimatedDate = estimatedDate;
        this.startDate = startDate;
        this.tasksCompleted = tasksCompleted;
        this.sparePartsChecked = sparePartsChecked;
        this.diagnosisValidated = diagnosisValidated;
        this.cleaningDone = cleaningDone;
        this.finalTestDone = finalTestDone;
    }
}