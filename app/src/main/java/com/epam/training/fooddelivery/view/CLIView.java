package com.epam.training.fooddelivery.view;

import com.epam.training.fooddelivery.aspect.EnableArgumentLogging;
import com.epam.training.fooddelivery.aspect.EnableExecutionTimeLogging;
import com.epam.training.fooddelivery.aspect.EnableReturnValueLogging;
import com.epam.training.fooddelivery.domain.*;
import com.epam.training.fooddelivery.utils.input.InputWrapper;
import com.epam.training.fooddelivery.utils.output.OutputWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Controller
public class CLIView implements View {

    OutputWrapper output;
    InputWrapper input;

    @Override
    @EnableExecutionTimeLogging
    @EnableReturnValueLogging
    public User readCredentials() {
        output.write("Enter customer email address:");
        String email = input.input();
        output.write("Enter customer password:");
        String password = input.input();
        return new User(email, password);
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableArgumentLogging
    public void printWelcomeMessage(Customer customer) {
        output.write("Welcome, " + customer.getName() + ". " + "Your balance is: " + customer.getBalance() + " EUR.");
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableArgumentLogging
    public void printAllFoods(List<Food> foods) {
        output.write("These are our goods today:");
        foods.forEach(food -> output.write("- " + food.getName() + " " + food.getPrice() + " EUR each"));
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableArgumentLogging
    @EnableReturnValueLogging
    public Food selectFood(List<Food> foods) {
        while (true) {
            output.write("Please enter the name of the food you would like to buy or delete from the cart:");
            String nameOfFood = input.input();
            List<String> nameOfFoods = foods.stream()
                    .map(Food::getName)
                    .toList();
            if (foods.stream().anyMatch(food -> !nameOfFoods.contains(nameOfFood))) {
                output.write("Invalid input");
            } else {
                return getFood(foods, nameOfFood);
            }
        }
    }

    private Food getFood(List<Food> foods, String nameOfFood) {
        return foods.stream()
                .filter(food -> food.getName().equals(nameOfFood))
                .findFirst()
                .orElse(null);
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableReturnValueLogging
    public int readPieces() {
        output.write("How many pieces do you want to buy? This input overwrites the old value in the cart, " +
                "entering zero removes the item completely:");
        return Integer.parseInt(input.input());
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableArgumentLogging
    public void printAddedToCart(Food food, int pieces) {
        if (pieces == 0) {
            output.write("Removed " + food.getName() + " from the cart.");
        } else {
            output.write("Added " + pieces + " piece(s) of " + food.getName() + " to the cart.");
        }
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableArgumentLogging
    public void printCart(Cart cart) {
        output.write("The cart has " + cart.getPrice() + " EUR of foods");
        cart.getOrderItems().forEach(orderItem -> output.write(
                orderItem.getFood().getName() + " " +
                        orderItem.getPieces() + " piece(s), " +
                        orderItem.getFood().getPrice().multiply(BigDecimal.valueOf(orderItem.getPieces())) + " EUR total"));
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableArgumentLogging
    public void printConfirmOrder(Order order) {
        List<String> nameOfFoods = order
                .getOrderItems()
                .stream()
                .map(ord -> ord.getFood().getName())
                .toList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        output.write("Order (items: " + nameOfFoods + ", price " + order.getPrice() + " EUR, timestamp: " +
                order.getTimestampCreated().format(formatter) + ") has been confirmed. Thank you for your purchase.");
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableReturnValueLogging
    public boolean promptOrder() {
        output.write("Are you finished with your order? (y/n)");
        String confirmInformation;
        while (true) {
            confirmInformation = input.input();
            if (confirmInformation.equals("y")) {
                return true;
            }
            if (confirmInformation.equals("n")) {
                return false;
            }
            output.write("Invalid input\nAre you finished with your order? (y/n)");
        }
    }

    @Override
    @EnableExecutionTimeLogging
    @EnableArgumentLogging
    public void printErrorMessage(String message) {
        output.write(message);
    }
}
