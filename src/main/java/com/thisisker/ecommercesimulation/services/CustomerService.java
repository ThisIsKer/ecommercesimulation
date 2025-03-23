package com.thisisker.ecommercesimulation.services;

import com.thisisker.ecommercesimulation.entities.Customer;
import com.thisisker.ecommercesimulation.repositories.CustomerRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}