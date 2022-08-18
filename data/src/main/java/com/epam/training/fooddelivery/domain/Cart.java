package com.epam.training.fooddelivery.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private BigDecimal price = BigDecimal.ZERO;
    private List<OrderItem> orderItems = new ArrayList<>();
}
