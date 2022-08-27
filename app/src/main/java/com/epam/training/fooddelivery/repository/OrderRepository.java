package com.epam.training.fooddelivery.repository;

import com.epam.training.fooddelivery.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerId(Long id);
    Optional<Order> findById(Long aLong);
}
