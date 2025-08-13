/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javastreamdemo.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 20113
 */
public class Order {
    private long id;
    private String customer;
    private LocalDateTime createdAt;
    private List<OrderItem> items;

    public Order() {}

    public Order(long id, String customer, LocalDateTime createdAt, List<OrderItem> items) {
        this.id = id; this.customer = customer; this.createdAt = createdAt; this.items = items;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}
