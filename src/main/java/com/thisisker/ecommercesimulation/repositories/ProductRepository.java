package com.thisisker.ecommercesimulation.repositories;

import com.thisisker.ecommercesimulation.entities.Customer;
import com.thisisker.ecommercesimulation.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
}
