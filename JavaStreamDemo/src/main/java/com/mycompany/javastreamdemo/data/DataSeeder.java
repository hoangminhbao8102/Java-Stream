/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javastreamdemo.data;

import com.mycompany.javastreamdemo.model.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 20113
 */
@Component
public class DataSeeder {
    public List<Product> products() {
        return List.of(
            new Product(1, "Laptop X", "Electronics", new BigDecimal("1500.00"), 10),
            new Product(2, "Phone Y", "Electronics", new BigDecimal("850.00"), 0),
            new Product(3, "Desk A", "Furniture", new BigDecimal("220.00"), 5),
            new Product(4, "Chair B", "Furniture", new BigDecimal("120.00"), 30),
            new Product(5, "Headset Z", "Accessories", new BigDecimal("79.90"), 50),
            new Product(6, "Monitor 27\"", "Electronics", new BigDecimal("299.00"), 7),
            new Product(7, "USB-C Cable", "Accessories", new BigDecimal("12.50"), 200)
        );
    }

    public List<Student> students() {
        return List.of(
            new Student(1, "An", "CNTT", 3.7, LocalDate.of(2004, 3, 1)),
            new Student(2, "Bình", "KTPM", 3.1, LocalDate.of(2003, 6, 11)),
            new Student(3, "Chi", "KHMT", 3.9, LocalDate.of(2004, 12, 20)),
            new Student(4, "Dũng", "CNTT", 2.8, LocalDate.of(2003, 2, 9)),
            new Student(5, "Hà", "TKDH", 3.4, LocalDate.of(2005, 5, 5))
        );
    }

    public List<Order> orders() {
        var ps = products();
        return List.of(
            new Order(1001, "Minh", LocalDateTime.now().minusDays(2), List.of(
                new OrderItem(ps.get(0), 1), // Laptop X
                new OrderItem(ps.get(6), 3)  // USB-C Cable
            )),
            new Order(1002, "Lan", LocalDateTime.now().minusDays(1), List.of(
                new OrderItem(ps.get(3), 4), // Chair B
                new OrderItem(ps.get(4), 2)  // Headset Z
            )),
            new Order(1003, "Bảo", LocalDateTime.now().minusHours(6), List.of(
                new OrderItem(ps.get(2), 1), // Desk A
                new OrderItem(ps.get(5), 2)  // Monitor 27"
            ))
        );
    }
}
