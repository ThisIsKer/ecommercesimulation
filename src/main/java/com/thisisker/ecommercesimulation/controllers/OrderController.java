package com.thisisker.ecommercesimulation.controllers;

import com.thisisker.ecommercesimulation.entities.Order;
import com.thisisker.ecommercesimulation.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, Object> request) {
        Long customerId = Long.valueOf(request.get("customerId").toString());
        List<Integer> productIdsInt = (List<Integer>) request.get("productIds");
        List<Long> productIds = productIdsInt.stream().map(Long::valueOf).toList();

        return ResponseEntity.ok(orderService.createOrder(customerId, productIds));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
