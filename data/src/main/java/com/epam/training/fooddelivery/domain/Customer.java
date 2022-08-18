package com.epam.training.fooddelivery.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal balance;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();
    @Transient
    private Cart cart = new Cart();
}
