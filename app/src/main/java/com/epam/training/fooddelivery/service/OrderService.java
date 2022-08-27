package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long customerId, Cart cart);
    List<Order> findAllOrdersByCustomerId(Long id);
    Order findOrderById(Long id);
}
