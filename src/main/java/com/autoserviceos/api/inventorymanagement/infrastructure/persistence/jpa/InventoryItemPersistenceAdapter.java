package com.autoserviceos.api.inventorymanagement.infrastructure.persistence.jpa;

import com.autoserviceos.api.inventorymanagement.domain.model.aggregates.InventoryItem;
import com.autoserviceos.api.inventorymanagement.domain.model.repositories.InventoryItemRepository;
import com.autoserviceos.api.inventorymanagement.infrastructure.persistence.jpa.mappers.InventoryItemPersistenceMapper;
import com.autoserviceos.api.inventorymanagement.infrastructure.persistence.jpa.repositories.SpringDataInventoryItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InventoryItemPersistenceAdapter implements InventoryItemRepository {
    private final SpringDataInventoryItemRepository repository;

    public InventoryItemPersistenceAdapter(SpringDataInventoryItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public InventoryItem save(InventoryItem item) {
        var entity = InventoryItemPersistenceMapper.toJpaEntity(item);
        return InventoryItemPersistenceMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<InventoryItem> findById(Long id) {
        return repository.findById(id).map(InventoryItemPersistenceMapper::toDomain);
    }

    @Override
    public List<InventoryItem> findAll() {
        return repository.findAll().stream().map(InventoryItemPersistenceMapper::toDomain).toList();
    }

    @Override
    public void delete(InventoryItem item) {
        repository.deleteById(item.getId());
    }
}