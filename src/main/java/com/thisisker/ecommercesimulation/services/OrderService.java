package com.thisisker.ecommercesimulation.services;

import com.thisisker.ecommercesimulation.commands.Command;
import com.thisisker.ecommercesimulation.commands.CreateOrderCommand;
import com.thisisker.ecommercesimulation.entities.Order;
import com.thisisker.ecommercesimulation.entities.OrderItem;
import com.thisisker.ecommercesimulation.entities.Product;
import com.thisisker.ecommercesimulation.entities.Customer;
import com.thisisker.ecommercesimulation.repositories.OrderRepository;
import com.thisisker.ecommercesimulation.repositories.OrderItemRepository;
import com.thisisker.ecommercesimulation.repositories.ProductRepository;
import com.thisisker.ecommercesimulation.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Order createOrder(Long customerId, List<OrderItem> orderItems) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);

        List<OrderItem> validOrderItems = orderItems.stream().map(orderItem -> {
            Product product = productRepository.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(validOrderItems);

        CreateOrderCommand createOrder = new CreateOrderCommand();
        createOrder.setOrder(order);
        createOrder.execute();

        return order;
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

        order.getOrderItems().clear();

        List<OrderItem> newOrderItems = updatedOrderItems.stream().map(orderItem -> {
            Product product = productRepository.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(newOrderItems);

        CreateOrderCommand createOrder = new CreateOrderCommand();
        createOrder.setOrder(order);
        createOrder.execute();

        return order;
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    public Page<Order> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable);
    }
}
