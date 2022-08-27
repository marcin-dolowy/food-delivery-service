package com.epam.training.fooddelivery.controller;

import com.epam.training.fooddelivery.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping
    @ResponseBody
    public String login() {
        return "";
    }

    @GetMapping
    @ResponseBody
    public String getId(HttpSession session) {
        Object id = session.getAttribute("id");
        return id.toString();
    }
}
