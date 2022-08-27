package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.exception.AuthenticationException;
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
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(() ->
                        new AuthenticationException("Incorrect email or password"));
    }
}
