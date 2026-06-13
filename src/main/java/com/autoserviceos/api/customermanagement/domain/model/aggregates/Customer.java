package com.autoserviceos.api.customermanagement.domain.model.aggregates;

/**
 * Pure domain aggregate root representing a Customer.
 * Protects business invariants and exposes intention-revealing behavior.
 */
public class Customer {

    private final Long id;
    private final String workshopId;
    private String fullName;
    private String dni;
    private String email;
    private String phone;

    private Customer(Long id, String workshopId, String fullName, String dni, String email, String phone) {
        this.id = id;
        this.workshopId = workshopId;
        this.fullName = fullName;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
    }

    public static Customer create(String workshopId, String fullName, String dni, String email, String phone) {
        return new Customer(null, workshopId, fullName, dni, email, phone);
    }

    public static Customer rehydrate(Long id, String workshopId, String fullName, String dni, String email, String phone) {
        return new Customer(id, workshopId, fullName, dni, email, phone);
    }

    public void update(String fullName, String dni, String email, String phone) {
        this.fullName = fullName;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public String getWorkshopId() { return workshopId; }
    public String getFullName() { return fullName; }
    public String getDni() { return dni; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}