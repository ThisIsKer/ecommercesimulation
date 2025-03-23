package com.thisisker.ecommercesimulation.repositories;

import com.thisisker.ecommercesimulation.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
