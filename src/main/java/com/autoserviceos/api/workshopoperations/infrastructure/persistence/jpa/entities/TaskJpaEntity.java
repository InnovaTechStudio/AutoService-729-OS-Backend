package com.autoserviceos.api.workshopoperations.infrastructure.persistence.jpa.entities;
import com.autoserviceos.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "tasks")
public class TaskJpaEntity extends AuditableModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private Long workOrderId;
    private Long mechanicId;
    @Column(columnDefinition = "TEXT") private String description;
    @Column(length = 50) private String status;
    @Column(length = 50) private String priority;
    private Integer estimatedTime;
    @Column(precision = 10, scale = 2) private BigDecimal laborPrice;
    @Column(columnDefinition = "TEXT") private String technicalDiagnosis;
    @Column(columnDefinition = "TEXT") private String customerExplanation;
    @Column(columnDefinition = "TEXT") private String internalObservation;
    @Column(length = 500) private String evidenceRegistered;
    @Column(length = 100) private String adminReviewStatus;

    public TaskJpaEntity(Long id, Long workOrderId, Long mechanicId, String description, String status, String priority, Integer estimatedTime, BigDecimal laborPrice, String technicalDiagnosis, String customerExplanation, String internalObservation, String evidenceRegistered, String adminReviewStatus) {
        this.id = id;
        this.workOrderId = workOrderId;
        this.mechanicId = mechanicId;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.estimatedTime = estimatedTime;
        this.laborPrice = laborPrice;
        this.technicalDiagnosis = technicalDiagnosis;
        this.customerExplanation = customerExplanation;
        this.internalObservation = internalObservation;
        this.evidenceRegistered = evidenceRegistered;
        this.adminReviewStatus = adminReviewStatus;
    }
}