package com.thisisker.ecommercesimulation.services;

import com.thisisker.ecommercesimulation.entities.Order;
import com.thisisker.ecommercesimulation.entities.Customer;
import com.thisisker.ecommercesimulation.entities.Product;
import com.thisisker.ecommercesimulation.repositories.OrderRepository;
import com.thisisker.ecommercesimulation.repositories.CustomerRepository;
import com.thisisker.ecommercesimulation.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Long customerId, List<Long> productIds) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new RuntimeException("No valid products found");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(products);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
