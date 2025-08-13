/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javastreamdemo.service;

import com.mycompany.javastreamdemo.data.DataSeeder;
import com.mycompany.javastreamdemo.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author 20113
 */
@Service
public class StreamDemoService {
    private final DataSeeder seeder;

    public StreamDemoService(DataSeeder seeder) { this.seeder = seeder; }

    // --- Basic datasets ---
    public List<Product> allProducts() { return seeder.products(); }
    public List<Student> allStudents() { return seeder.students(); }
    public List<Order> allOrders() { return seeder.orders(); }

    // --- Products: map/filter/reduce/sort ---
    public List<Product> inStockSortedByPriceAsc() {
        return seeder.products().stream()
            .filter(p -> p.getStock() > 0)
            .sorted(Comparator.comparing(Product::getPrice))
            .toList();
    }

    public Optional<Product> mostExpensive() {
        return seeder.products().stream()
            .max(Comparator.comparing(Product::getPrice));
    }

    public BigDecimal totalInventoryValue() {
        return seeder.products().stream()
            .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getStock())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, Long> countByCategory() {
        return seeder.products().stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    public Map<String, BigDecimal> avgPriceByCategory() {
        return seeder.products().stream().collect(
            Collectors.groupingBy(Product::getCategory,
                Collector.of(
                    () -> new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO},
                    (arr, p) -> { arr[0] = arr[0].add(p.getPrice()); arr[1] = arr[1].add(BigDecimal.ONE); },
                    (a, b) -> new BigDecimal[]{ a[0].add(b[0]), a[1].add(b[1]) },
                    arr -> arr[1].compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : arr[0].divide(arr[1], 2, RoundingMode.HALF_UP)
                )
            )
        );
    }

    // --- Students: grouping, partitioning, stats ---
    public Map<String, Double> avgGpaByMajor() {
        return seeder.students().stream()
            .collect(Collectors.groupingBy(Student::getMajor, Collectors.averagingDouble(Student::getGpa)));
    }

    public Map<Boolean, List<Student>> partitionByHonors(double threshold) {
        return seeder.students().stream()
            .collect(Collectors.partitioningBy(s -> s.getGpa() >= threshold));
    }

    public DoubleSummaryStatistics gpaStats() {
        return seeder.students().stream().collect(Collectors.summarizingDouble(Student::getGpa));
    }

    // --- Orders: flatMap and item stats ---
    public List<String> allOrderedProductNamesDistinct() {
        return seeder.orders().stream()
            .flatMap(o -> o.getItems().stream())
            .map(oi -> oi.getProduct().getName())
            .distinct()
            .sorted()
            .toList();
    }

    public Map<String, Integer> totalQuantityByProduct() {
        return seeder.orders().stream()
            .flatMap(o -> o.getItems().stream())
            .collect(Collectors.toMap(
                oi -> oi.getProduct().getName(),
                OrderItem::getQuantity,
                Integer::sum
            ));
    }

    public BigDecimal revenue() {
        return seeder.orders().stream()
            .flatMap(o -> o.getItems().stream())
            .map(oi -> oi.getProduct().getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // --- Optional & find ---
    public Optional<Product> findByNameIgnoreCase(String name) {
        return seeder.products().stream()
            .filter(p -> p.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    // --- Parallel stream demo (toy CPU work) ---
    public Map<String, Long> compareSequentialVsParallel(int n) {
        // Sum of squares 1..n
        long t1 = time(() -> IntStream.rangeClosed(1, n).map(i -> i * i).sum());
        long t2 = time(() -> IntStream.rangeClosed(1, n).parallel().map(i -> i * i).sum());
        return Map.of("sequentialMillis", t1, "parallelMillis", t2);
    }

    private long time(Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        return System.currentTimeMillis() - start;
    }
}
