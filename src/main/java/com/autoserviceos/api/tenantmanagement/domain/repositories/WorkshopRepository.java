package com.autoserviceos.api.tenantmanagement.domain.repositories;

import com.autoserviceos.api.tenantmanagement.domain.model.aggregates.Workshop;

import java.util.List;
import java.util.Optional;

public interface WorkshopRepository {
    List<Workshop> findAll();
    Optional<Workshop> findById(Long id);
    Workshop save(Workshop workshop);
}