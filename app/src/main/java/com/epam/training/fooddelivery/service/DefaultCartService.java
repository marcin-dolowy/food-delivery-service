package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.domain.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultCartService implements CartService {

    @Override
    @Transactional
    public void updateCart(Customer customer, Food food, int pieces) {
        boolean isInCard = false;
        List<OrderItem> customerOrder = customer.getCart().getOrderItems();
        for (OrderItem orderItem : customerOrder) {
            if (orderItem.getFood().getName().equals(food.getName())) {
                isInCard = true;
                if (pieces == 0) {
                    customer.getCart().setPrice(customer.getCart().getPrice().add(orderItem.getPrice()));
                    customer.getCart().getOrderItems().remove(orderItem);
                } else {
                    orderItem.setPieces(pieces);
                    orderItem.setPrice(orderItem.getFood().getPrice().multiply(BigDecimal.valueOf(pieces)));
                }
                break;
            }
        }
        if (!isInCard) {
            OrderItem orderItem = new OrderItem(pieces, food);
            addOrderItem(orderItem, customer);
        }
        updateCardPrice(customer);
    }

    private void updateCardPrice(Customer customer) {
        customer.getCart().setPrice(BigDecimal.ZERO);
        customer.getCart()
                .getOrderItems()
                .forEach(orderItem -> customer.getCart().setPrice(customer.getCart().getPrice().add(orderItem.getPrice())));
    }

    private void addOrderItem(OrderItem orderItem, Customer customer) {
        Cart cart = customer.getCart();
        cart.setPrice(new BigDecimal(0));
        cart.setPrice(cart.getPrice().add(orderItem.getPrice()));
        cart.getOrderItems().add(orderItem);
    }
}