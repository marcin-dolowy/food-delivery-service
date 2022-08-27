package com.epam.training.fooddelivery.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

//    @Autowired
//    private CustomerRepository customerRepository;

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
