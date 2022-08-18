package com.epam.training.fooddelivery;

import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.service.*;
import com.epam.training.fooddelivery.view.View;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FoodDelivery implements CommandLineRunner {

    private CustomerService customerService;
    private CartService cartService;
    private FoodService foodService;
    private OrderService orderService;
    private View view;

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        boolean isAuthenticated = false;

        try {
            customer = customerService.authenticate(view.readCredentials());
            isAuthenticated = true;
        } catch (AuthenticationException exception) {
            view.printErrorMessage(exception.getMessage());
        }
        if (isAuthenticated) {
            view.printWelcomeMessage(customer);

            boolean isCorrect;
            Order order = null;

            do {
                view.printAllFoods(foodService.listAllFood());
                Food food = view.selectFood(foodService.listAllFood());
                int pieces = view.readPieces();
                cartService.updateCart(customer, food, pieces);
                view.printAddedToCart(food, pieces);
                view.printCart(customer.getCart());
                isCorrect = view.promptOrder();
                if (isCorrect) {
                    try {
                        order = orderService.createOrder(customer);
                    } catch (LowBalanceException exception) {
                        view.printErrorMessage(exception.getMessage());
                        isCorrect = false;
                    }
                }
            } while (!isCorrect);

            view.printConfirmOrder(order);
        }
    }
}
