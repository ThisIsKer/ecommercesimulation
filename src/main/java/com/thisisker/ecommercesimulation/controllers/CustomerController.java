package com.thisisker.ecommercesimulation.controllers;

import com.thisisker.ecommercesimulation.entities.Customer;
import com.thisisker.ecommercesimulation.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/customers")
    public Page<Customer> getCustomers(@RequestParam int page, @RequestParam int size) {
        return customerService.getAllCustomers(page, size);
    }
}
