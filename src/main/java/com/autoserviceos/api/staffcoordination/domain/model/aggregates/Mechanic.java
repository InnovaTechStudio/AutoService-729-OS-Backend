package com.autoserviceos.api.staffcoordination.domain.model.aggregates;

import jakarta.persistence.*;

@Entity
@Table(name = "mechanics")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private Long workshopId;

    @Column(nullable = false)
    private Boolean active;

    protected Mechanic() {
    }

    public Mechanic(String firstName, String lastName, String email, String phone, String specialty, Long workshopId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
        this.workshopId = workshopId;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public Boolean getActive() {
        return active;
    }
}