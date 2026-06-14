package com.autoserviceos.api.workshopoperations.domain.model.aggregates;

import java.math.BigDecimal;

/**
 * Aggregate root representing a specific diagnostic or repair job within a WorkOrder.
 */
public class Task {

    private final Long id;
    private final Long workOrderId;
    private Long mechanicId;
    private String description;
    private String status;
    private String priority;
    private Integer estimatedTime;
    private BigDecimal laborPrice;
    private String technicalDiagnosis;
    private String customerExplanation;
    private String internalObservation;
    private String evidenceRegistered;
    private String adminReviewStatus;

    private Task(Long id, Long workOrderId, Long mechanicId, String description, String status, String priority, Integer estimatedTime, BigDecimal laborPrice,
                 String technicalDiagnosis, String customerExplanation, String internalObservation, String evidenceRegistered, String adminReviewStatus) {
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

    public static Task create(Long workOrderId, Long mechanicId, String description, String priority, Integer estimatedTime, BigDecimal laborPrice) {
        return new Task(null, workOrderId, mechanicId, description, "PENDING", priority, estimatedTime, laborPrice, "", "", "", "", "");
    }

    public static Task rehydrate(Long id, Long workOrderId, Long mechanicId, String description, String status, String priority, Integer estimatedTime, BigDecimal laborPrice,
                                 String technicalDiagnosis, String customerExplanation, String internalObservation, String evidenceRegistered, String adminReviewStatus) {
        return new Task(id, workOrderId, mechanicId, description, status, priority, estimatedTime, laborPrice, technicalDiagnosis, customerExplanation, internalObservation, evidenceRegistered, adminReviewStatus);
    }

    public void update(String description, String status, String priority, Integer estimatedTime, BigDecimal laborPrice, Long mechanicId) {
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.estimatedTime = estimatedTime;
        this.laborPrice = laborPrice;
        this.mechanicId = mechanicId;
    }

    public void patchTechnicalData(String status, String diagnosis, String explanation, String observation, String evidence, String reviewStatus) {
        if (status != null && !status.trim().isEmpty()) this.status = status;
        this.technicalDiagnosis = diagnosis != null ? diagnosis : this.technicalDiagnosis;
        this.customerExplanation = explanation != null ? explanation : this.customerExplanation;
        this.internalObservation = observation != null ? observation : this.internalObservation;
        this.evidenceRegistered = evidence != null ? evidence : this.evidenceRegistered;
        this.adminReviewStatus = reviewStatus != null ? reviewStatus : this.adminReviewStatus;
    }

    // Getters
    public Long getId() { return id; }
    public Long getWorkOrderId() { return workOrderId; }
    public Long getMechanicId() { return mechanicId; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getPriority() { return priority; }
    public Integer getEstimatedTime() { return estimatedTime; }
    public BigDecimal getLaborPrice() { return laborPrice; }
    public String getTechnicalDiagnosis() { return technicalDiagnosis; }
    public String getCustomerExplanation() { return customerExplanation; }
    public String getInternalObservation() { return internalObservation; }
    public String getEvidenceRegistered() { return evidenceRegistered; }
    public String getAdminReviewStatus() { return adminReviewStatus; }
}