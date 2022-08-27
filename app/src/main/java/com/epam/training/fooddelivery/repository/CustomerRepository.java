package com.epam.training.fooddelivery.repository;

import com.epam.training.fooddelivery.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    List<Customer> findAll();
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders WHERE c.email = :email")
    Optional<Customer> findCustomerByEmail(@Param("email") String email);
}
