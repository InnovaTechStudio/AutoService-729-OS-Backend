package com.autoserviceos.api.tenantmanagement.application.commandservices;

import com.autoserviceos.api.tenantmanagement.application.commands.CreateWorkshopCommand;
import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;
import java.util.Optional;

/**
 * Application service port defining workshop write operations.
 */
public interface WorkshopCommandService {
    Optional<Workshop> handle(CreateWorkshopCommand command);
}