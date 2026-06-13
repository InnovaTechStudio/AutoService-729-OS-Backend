package com.autoserviceos.api.fleetmanagement.domain.model.aggregates;

/**
 * Aggregate root representing a Vehicle within the Fleet Management domain.
 * Protects business invariants related to vehicle registration and status tracking.
 */
public class Vehicle {

    private final Long id;
    private String plate;
    private String brand;
    private String model;
    private String year;
    private String color;
    private String status;
    private String image;
    private Long customerId;

    private Vehicle(Long id, String plate, String brand, String model, String year, String color, String status, String image, Long customerId) {
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

    /**
     * Factory method to initialize a new Vehicle instance with specified technical and owner details.
     */
    public static Vehicle create(String plate, String brand, String model, String year, String color, String status, String image, Long customerId) {
        return new Vehicle(null, plate, brand, model, year, color, status, image, customerId);
    }

    /**
     * Factory method to reconstruct an existing Vehicle aggregate from persistence data.
     */
    public static Vehicle rehydrate(Long id, String plate, String brand, String model, String year, String color, String status, String image, Long customerId) {
        return new Vehicle(id, plate, brand, model, year, color, status, image, customerId);
    }

    /**
     * Updates the vehicle's technical details, status, and owner assignment.
     */
    public void update(String plate, String brand, String model, String year, String color, String status, String image, Long customerId) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.status = status;
        this.image = image;
        this.customerId = customerId;
    }

    // Getters
    public Long getId() { return id; }
    public String getPlate() { return plate; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getYear() { return year; }
    public String getColor() { return color; }
    public String getStatus() { return status; }
    public String getImage() { return image; }
    public Long getCustomerId() { return customerId; }
}