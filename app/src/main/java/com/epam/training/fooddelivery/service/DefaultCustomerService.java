package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.User;
import com.epam.training.fooddelivery.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class DefaultCustomerService implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer authenticate(User user) {
        return customerRepository.findCustomerByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(() ->
                        new AuthenticationException("Incorrect email or password"));
    }
}
