package com.autoserviceos.api.iam.infrastructure.persistence.jpa.entities;

import com.autoserviceos.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JPA entity representing user persistence structure mapped onto the database.
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserJpaEntity extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 30)
    private String role;

    @Column(nullable = false, length = 50)
    private String workshopId;

    public UserJpaEntity(Long id, String email, String passwordHash, String role, String workshopId) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.workshopId = workshopId;
    }
}