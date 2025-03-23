package com.thisisker.ecommercesimulation.services;

import com.thisisker.ecommercesimulation.entities.Order;
import com.thisisker.ecommercesimulation.entities.OrderItem;
import com.thisisker.ecommercesimulation.entities.Product;
import com.thisisker.ecommercesimulation.entities.Customer;
import com.thisisker.ecommercesimulation.repositories.OrderRepository;
import com.thisisker.ecommercesimulation.repositories.OrderItemRepository;
import com.thisisker.ecommercesimulation.repositories.ProductRepository;
import com.thisisker.ecommercesimulation.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                        ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Order createOrder(Long customerId, List<OrderItem> orderItems) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);

        // Validate and attach products to order items
        List<OrderItem> validOrderItems = orderItems.stream().map(orderItem -> {
            Product product = productRepository.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(validOrderItems);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order updateOrder(Long orderId, List<OrderItem> updatedOrderItems) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Remove old order items
        order.getOrderItems().clear();

        // Add updated order items
        List<OrderItem> newOrderItems = updatedOrderItems.stream().map(orderItem -> {
            Product product = productRepository.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(newOrderItems);
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}
