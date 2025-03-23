package com.thisisker.ecommercesimulation.repositories;

import com.thisisker.ecommercesimulation.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
