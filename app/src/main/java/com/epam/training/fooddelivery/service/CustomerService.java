package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Customer;

public interface CustomerService {
    Customer findCustomerByEmail(String email);

}
