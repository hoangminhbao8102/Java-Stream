/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javastreamdemo.web;

import com.mycompany.javastreamdemo.service.StreamDemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 20113
 */
@Controller
public class HomeController {
    private final StreamDemoService service;
    public HomeController(StreamDemoService service) { this.service = service; }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Java Stream – Demos");
        model.addAttribute("productsCount", service.allProducts().size());
        model.addAttribute("studentsCount", service.allStudents().size());
        model.addAttribute("ordersCount", service.allOrders().size());
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("all", service.allProducts());
        model.addAttribute("inStock", service.inStockSortedByPriceAsc());
        model.addAttribute("maxPrice", service.mostExpensive().orElse(null));
        model.addAttribute("totalValue", service.totalInventoryValue());
        model.addAttribute("countByCategory", service.countByCategory());
        model.addAttribute("avgPriceByCategory", service.avgPriceByCategory());
        return "products";
    }

    @GetMapping("/students")
    public String students(Model model, @RequestParam(defaultValue = "3.5") double honors) {
        model.addAttribute("all", service.allStudents());
        model.addAttribute("avgGpaByMajor", service.avgGpaByMajor());
        model.addAttribute("partition", service.partitionByHonors(honors));
        model.addAttribute("stats", service.gpaStats());
        model.addAttribute("honors", honors);
        return "students";
    }

    @GetMapping("/collectors")
    public String collectors(Model model) {
        model.addAttribute("orderedNames", service.allOrderedProductNamesDistinct());
        model.addAttribute("qtyByProduct", service.totalQuantityByProduct());
        model.addAttribute("revenue", service.revenue());
        return "collectors";
    }

    @GetMapping("/parallel")
    public String parallel(@RequestParam(defaultValue = "5_000_000") int n, Model model) {
        model.addAttribute("n", n);
        model.addAttribute("timing", service.compareSequentialVsParallel(n));
        return "parallel";
    }
}
