package com.autoserviceos.api.workshopoperations.domain.model.aggregates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Aggregate root managing vehicle processing folders and checklist execution progress within a tenant scope.
 */
public class WorkOrder {

    private final Long id;
    private final String workshopId;
    private final String trackingCode;
    private final Long vehicleId;
    private final Long customerId;
    private final Long mechanicId;
    private String description;
    private String status;
    private BigDecimal price;
    private String estimatedDate;
    private final String startDate;
    private Boolean tasksCompleted;
    private Boolean sparePartsChecked;
    private Boolean diagnosisValidated;
    private Boolean cleaningDone;
    private Boolean finalTestDone;

    private WorkOrder(Long id, String workshopId, String trackingCode, Long vehicleId, Long customerId, Long mechanicId,
                      String description, String status, BigDecimal price, String estimatedDate, String startDate,
                      Boolean tasksCompleted, Boolean sparePartsChecked, Boolean diagnosisValidated, Boolean cleaningDone, Boolean finalTestDone) {
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

    public static WorkOrder create(String workshopId, Long vehicleId, Long customerId, Long mechanicId, String description, String estimatedDate, BigDecimal price) {
        String generatedCode = "WO-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        String currentDate = LocalDate.now().toString();
        return new WorkOrder(null, workshopId, generatedCode, vehicleId, customerId, mechanicId, description, "PENDING", price, estimatedDate, currentDate, false, false, false, false, false);
    }

    public static WorkOrder rehydrate(Long id, String workshopId, String trackingCode, Long vehicleId, Long customerId, Long mechanicId,
                                      String description, String status, BigDecimal price, String estimatedDate, String startDate,
                                      Boolean tasksCompleted, Boolean sparePartsChecked, Boolean diagnosisValidated, Boolean cleaningDone, Boolean finalTestDone) {
        return new WorkOrder(id, workshopId, trackingCode, vehicleId, customerId, mechanicId, description, status, price, estimatedDate, startDate, tasksCompleted, sparePartsChecked, diagnosisValidated, cleaningDone, finalTestDone);
    }

    public void update(String description, String estimatedDate, BigDecimal price) {
        this.description = description;
        this.estimatedDate = estimatedDate;
        this.price = price;
    }

    public void updateChecklist(Boolean tasksCompleted, Boolean sparePartsChecked, Boolean diagnosisValidated, Boolean cleaningDone, Boolean finalTestDone) {
        this.tasksCompleted = tasksCompleted;
        this.sparePartsChecked = sparePartsChecked;
        this.diagnosisValidated = diagnosisValidated;
        this.cleaningDone = cleaningDone;
        this.finalTestDone = finalTestDone;
    }

    public void updateStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status;
        }
    }

    // Getters
    public Long getId() { return id; }
    public String getWorkshopId() { return workshopId; }
    public String getTrackingCode() { return trackingCode; }
    public Long getVehicleId() { return vehicleId; }
    public Long getCustomerId() { return customerId; }
    public Long getMechanicId() { return mechanicId; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public BigDecimal getPrice() { return price; }
    public String getEstimatedDate() { return estimatedDate; }
    public String getStartDate() { return startDate; }
    public Boolean getTasksCompleted() { return tasksCompleted; }
    public Boolean getSparePartsChecked() { return sparePartsChecked; }
    public Boolean getDiagnosisValidated() { return diagnosisValidated; }
    public Boolean getCleaningDone() { return cleaningDone; }
    public Boolean getFinalTestDone() { return finalTestDone; }
}