package com.thisisker.ecommercesimulation.commands;

import com.thisisker.ecommercesimulation.entities.Order;

public class CheckStockCommand implements Command {
    private final Order order;

    public CheckStockCommand(Order order){
        this.order = order;
    }

    @Override
    public void execute() {
        if (order == null) {
            throw new IllegalStateException("Order has not been set for CheckStockCommand.");
        }

        order.getOrderItems().forEach(orderItem -> {
            if (orderItem.getProduct().getStock() < orderItem.getQuantity()) {
                throw new IllegalStateException("Not enough stock for product: " + orderItem.getProduct().getName());
            }
        });
    }
}
