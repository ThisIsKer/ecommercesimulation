package com.thisisker.ecommercesimulation.repositories;

import com.thisisker.ecommercesimulation.entities.Customer;
import com.thisisker.ecommercesimulation.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {
}
