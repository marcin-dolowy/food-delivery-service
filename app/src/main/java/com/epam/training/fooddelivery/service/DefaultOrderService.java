package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.exception.LowBalanceException;
import com.epam.training.fooddelivery.repository.CustomerRepository;
import com.epam.training.fooddelivery.repository.OrderItemRepository;
import com.epam.training.fooddelivery.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DefaultOrderService implements OrderService {

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Order createOrder(Long customerId, Cart cart) {
        //TUTAJ ZMIANA
        Customer customer = customerRepository.findById(customerId).get();
        if (customer.getCart().getOrderItems().isEmpty()) {
            throw new IllegalStateException("Your cart is empty");
        }
        if (customer.getBalance().compareTo(customer.getCart().getPrice()) >= 0) {

            Order order = new Order();
            order.setCustomer(customer);
            order.setPrice(customer.getCart().getPrice());
            order.setOrderItems(customer.getCart().getOrderItems());

            customer.setBalance(customer.getBalance().subtract(order.getPrice()));
            order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
            saveCustomer(customer, order);
            orderItemRepository.saveAll(order.getOrderItems());
            orderRepository.save(order);
            customerRepository.save(customer);

            return order;

        } else {
            throw new LowBalanceException("You don't have enough money, your balance is only " + customer.getBalance() +
                    " EUR. We do not empty your cart, please remove some of the items.");
        }
    }

    @Override
    public List<Order> findAllOrdersByCustomerId(Long id) {
        return orderRepository.findAllByCustomerId(id);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    private void saveCustomer(Customer customer, Order order) {
        List<Order> orders = customer.getOrders();
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
        customer.setOrders(orders);
    }

}


