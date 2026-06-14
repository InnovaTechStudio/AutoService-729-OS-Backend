package com.autoserviceos.api.tenantmanagement.domain.model.aggregates;

import java.util.UUID;

/**
 * Aggregate root representing a business Workshop tenant within the system.
 * Manages the corporate identity and generates unique tenant identifiers.
 */
public class Workshop {

    private final Long id;
    private String name;
    private final String tenantId;

    private Workshop(Long id, String name, String tenantId) {
        this.id = id;
        this.name = name;
        this.tenantId = tenantId;
    }

    /**
     * Factory method to instantiate a new Workshop tenant.
     * Automatically generates a unique tenant identifier in the format "WS-XXXX".
     *
     * @param name The commercial or corporate name of the workshop.
     * @return A new instance of Workshop.
     */
    public static Workshop create(String name) {
        String generatedTenantId = "WS-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return new Workshop(null, name, generatedTenantId);
    }

    /**
     * Factory method to reconstruct an existing Workshop aggregate from data stores.
     */
    public static Workshop rehydrate(Long id, String name, String tenantId) {
        return new Workshop(id, name, tenantId);
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getTenantId() { return tenantId; }
}