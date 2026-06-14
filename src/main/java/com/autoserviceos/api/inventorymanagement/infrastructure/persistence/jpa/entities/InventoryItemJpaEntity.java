package com.autoserviceos.api.inventorymanagement.infrastructure.persistence.jpa.entities;

import com.autoserviceos.api.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/** JPA entity mapped to the inventory_items table in the database. */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "inventory_items")
public class InventoryItemJpaEntity extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String sku;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer minStock;

    @Column(columnDefinition = "TEXT")
    private String image;

    public InventoryItemJpaEntity(Long id, String sku, String name, String category, String brand,
                                  BigDecimal unitPrice, Integer stock, Integer minStock, String image) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.minStock = minStock;
        this.image = image;
    }
}