package com.epam.training.fooddelivery.repository;

import com.epam.training.fooddelivery.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByEmailAndPassword(String password, String email);
}
