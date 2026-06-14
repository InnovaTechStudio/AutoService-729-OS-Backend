package com.autoserviceos.api.iam.domain.model.aggregates;

/**
 * Aggregate root representing an authenticated User within the IAM context.
 * Manages user credentials, role assignments, and workshop alignment.
 */
public class User {

    private final Long id;
    private final String email;
    private String passwordHash;
    private final String role;
    private final String workshopId;

    private User(Long id, String email, String passwordHash, String role, String workshopId) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.workshopId = workshopId;
    }

    /**
     * Factory method to instantiate a new User aggregate root.
     *
     * @param email The unique identifier email address.
     * @param passwordHash The securely pre-hashed password.
     * @param role The authorization access role.
     * @param workshopId The tenant workshop context scope identifier.
     * @return A new instance of User.
     */
    public static User create(String email, String passwordHash, String role, String workshopId) {
        return new User(null, email, passwordHash, role, workshopId);
    }

    /**
     * Factory method to reconstruct an existing User aggregate from data stores.
     */
    public static User rehydrate(Long id, String email, String passwordHash, String role, String workshopId) {
        return new User(id, email, passwordHash, role, workshopId);
    }

    /**
     * Securely overwrites the current password hash state.
     *
     * @param newPasswordHash The new pre-hashed password string.
     */
    public void updatePassword(String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }

    // Getters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public String getWorkshopId() { return workshopId; }
}