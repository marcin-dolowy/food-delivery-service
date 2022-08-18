package com.epam.training.fooddelivery.utils.output;


import org.springframework.stereotype.Component;

@Component
public class CLIOutput implements OutputWrapper {
    @Override
    public void write(String input) {
        System.out.println(input);
    }
}
