package com.epam.training.fooddelivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int pieces;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public OrderItem(int pieces, Food food) {
        this.pieces = pieces;
        this.food = food;
        this.price = food.getPrice().multiply(BigDecimal.valueOf(pieces));
    }
}


