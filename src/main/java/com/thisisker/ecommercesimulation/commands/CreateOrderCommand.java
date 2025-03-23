package com.thisisker.ecommercesimulation.commands;

import com.thisisker.ecommercesimulation.entities.Order;
import com.thisisker.ecommercesimulation.repositories.OrderRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateOrderCommand implements Command {
    @Setter
    private Order order;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public void execute() {
        if (order == null) {
            throw new IllegalStateException("Order has not been set for CreateOrderCommand.");
        }

        Command checkStock = new CheckStockCommand(order);
        Command updateStock = new UpdateStockCommand(order);
        checkStock.execute();
        updateStock.execute();

        orderRepository.save(order);
    }
}