package com.thisisker.ecommercesimulation.controllers;

import com.thisisker.ecommercesimulation.entities.Order;
import com.thisisker.ecommercesimulation.entities.OrderItem;
import com.thisisker.ecommercesimulation.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam Long customerId, @RequestBody List<OrderItem> orderItems) {
        Order newOrder = orderService.createOrder(customerId, orderItems);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody List<OrderItem> orderItems) {
        Order updatedOrder = orderService.updateOrder(id, orderItems);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders")
    public Page<Order> getOrders(@RequestParam int page, @RequestParam int size) {
        return orderService.getAllOrders(page, size);
    }
}
