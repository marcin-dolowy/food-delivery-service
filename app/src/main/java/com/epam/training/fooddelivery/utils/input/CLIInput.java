package com.epam.training.fooddelivery.utils.input;

import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class CLIInput implements InputWrapper {
    @Override
    public String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
