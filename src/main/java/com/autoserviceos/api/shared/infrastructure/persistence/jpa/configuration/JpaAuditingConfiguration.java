package com.autoserviceos.api.shared.infrastructure.persistence.jpa.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuration class to enable JPA Auditing.
 * This allows Spring Data JPA to automatically populate auditing fields (like @CreatedDate).
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
}