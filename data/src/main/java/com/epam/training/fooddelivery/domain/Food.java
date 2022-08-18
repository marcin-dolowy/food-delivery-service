package com.epam.training.fooddelivery.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal calorie;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private Category category;
}
