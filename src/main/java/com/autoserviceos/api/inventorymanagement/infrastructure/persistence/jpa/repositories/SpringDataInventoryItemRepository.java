package com.autoserviceos.api.inventorymanagement.infrastructure.persistence.jpa.repositories;
import com.autoserviceos.api.inventorymanagement.infrastructure.persistence.jpa.entities.InventoryItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SpringDataInventoryItemRepository extends JpaRepository<InventoryItemJpaEntity, Long> {}