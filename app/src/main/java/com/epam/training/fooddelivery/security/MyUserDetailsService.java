package com.epam.training.fooddelivery.security;

import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(email);
        if (customerByEmail.isPresent()) {
            return new MyUserPrincipal(customerByEmail.get());
        } else {
            return null;
        }
    }
}
