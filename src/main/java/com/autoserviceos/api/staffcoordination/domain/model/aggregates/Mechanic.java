package com.autoserviceos.api.staffcoordination.domain.model.aggregates;

/**
 * Aggregate root representing a Mechanic within the Staff Coordination domain.
 * Manages the profile, capacity metrics, and workshop assignment of a technical staff member.
 */
public class Mechanic {

    private final Long id;
    private String fullName;
    private String specialty;
    private String email;
    private Integer maxCapacity;
    private final String workshopId;

    private Mechanic(Long id, String fullName, String specialty, Integer maxCapacity, String email, String workshopId) {
        this.id = id;
        this.fullName = fullName;
        this.specialty = specialty;
        this.maxCapacity = maxCapacity;
        this.email = email;
        this.workshopId = workshopId;
    }

    /**
     * Factory method to initialize a new Mechanic instance.
     */
    public static Mechanic create(String fullName, String specialty, Integer maxCapacity, String email, String workshopId) {
        return new Mechanic(null, fullName, specialty, maxCapacity, email, workshopId);
    }

    /**
     * Factory method to reconstruct an existing Mechanic aggregate from persistence data.
     */
    public static Mechanic rehydrate(Long id, String fullName, String specialty, Integer maxCapacity, String email, String workshopId) {
        return new Mechanic(id, fullName, specialty, maxCapacity, email, workshopId);
    }

    /**
     * Updates core profile structures, specialized capability metadata, and contact details records.
     */
    public void update(String fullName, String specialty, Integer maxCapacity, String email) {
        this.fullName = fullName;
        this.specialty = specialty;
        this.maxCapacity = maxCapacity;
        this.email = email;
    }

    // Getters
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getSpecialty() { return specialty; }
    public Integer getMaxCapacity() { return maxCapacity; }
    public String getEmail() { return email; }
    public String getWorkshopId() { return workshopId; }
}