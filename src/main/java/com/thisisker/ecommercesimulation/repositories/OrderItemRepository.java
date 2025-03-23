package com.thisisker.ecommercesimulation.repositories;

import com.thisisker.ecommercesimulation.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
