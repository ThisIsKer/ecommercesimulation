package com.thisisker.ecommercesimulation.commands;

import com.thisisker.ecommercesimulation.entities.Order;

public class UpdateStockCommand implements Command {
    private final Order order;

    public UpdateStockCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        if (order == null) {
            throw new IllegalStateException("Order has not been set for UpdateStockCommand.");
        }

        order.getOrderItems().forEach(orderItem -> {
            orderItem.getProduct().setStock(orderItem.getProduct().getStock() - orderItem.getQuantity());
        });
    }
}