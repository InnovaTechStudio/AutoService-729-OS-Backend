package com.autoserviceos.api.inventorymanagement.domain.model.aggregates;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Aggregate root representing an Inventory Item within the Inventory Management domain.
 * Safeguards stock constraints, pricing logic, and SKU generation.
 */
public class InventoryItem {

    private final Long id;
    private final String sku;
    private String name;
    private String category;
    private String brand;
    private BigDecimal unitPrice;
    private Integer stock;
    private Integer minStock;
    private String image;

    private InventoryItem(Long id, String sku, String name, String category, String brand, BigDecimal unitPrice, Integer stock, Integer minStock, String image) {
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

    /**
     * Factory method to initialize a new Inventory Item.
     * Automatically generates a unique internal SKU code.
     */
    public static InventoryItem create(String name, String category, String brand,
                                       BigDecimal unitPrice, Integer stock, Integer minStock, String image) {
        String generatedSku = "SKU-" + (new Random().nextInt(9000) + 1000);
        return new InventoryItem(null, generatedSku, name, category, brand, unitPrice, stock, minStock, image);
    }

    /**
     * Factory method to reconstruct an existing Inventory Item aggregate from persistence data.
     */
    public static InventoryItem rehydrate(Long id, String sku, String name, String category,
                                          String brand, BigDecimal unitPrice, Integer stock, Integer minStock, String image) {
        return new InventoryItem(id, sku, name, category, brand, unitPrice, stock, minStock, image);
    }

    /**
     * Updates the stock details, pricing structure, and metadata tracking configurations.
     */
    public void update(String name, String category, String brand, BigDecimal unitPrice, Integer stock, Integer minStock, String image) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.minStock = minStock;
        this.image = image;
    }

    // Getters
    public Long getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public Integer getStock() { return stock; }
    public Integer getMinStock() { return minStock; }
    public String getImage() { return image; }
}