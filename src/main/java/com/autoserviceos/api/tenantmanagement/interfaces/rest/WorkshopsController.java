package com.autoserviceos.api.tenantmanagement.interfaces.rest;

import com.autoserviceos.api.tenantmanagement.domain.model.repositories.WorkshopRepository;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Exposes endpoints for the authenticated user's own workshop profile.
 */
@RestController
@RequestMapping("/api/v1/workshops")
@Tag(name = "Workshops", description = "Endpoints for the authenticated workshop's own profile")
public class WorkshopsController {

    private final WorkshopRepository workshopRepository;

    public WorkshopsController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    private String getAuthenticatedWorkshopId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getCredentials() instanceof Claims claims) {
            return claims.get("workshopId", String.class);
        }
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentWorkshop() {
        String tenantId = getAuthenticatedWorkshopId();
        if (tenantId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return workshopRepository.findByTenantId(tenantId)
                .map(workshop -> ResponseEntity.ok(Map.of("tenantId", tenantId, "name", workshop.getName())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
