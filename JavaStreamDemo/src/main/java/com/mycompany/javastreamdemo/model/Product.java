/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javastreamdemo.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author 20113
 */
public class Product {
    private long id;
    private String name;
    private String category;
    private BigDecimal price;
    private int stock;

    public Product() {}

    public Product(long id, String name, String category, BigDecimal price, int stock) {
        this.id = id; this.name = name; this.category = category; this.price = price; this.stock = stock;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product p)) return false;
        return id == p.id;
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
