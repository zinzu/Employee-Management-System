package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){

        return "employees/login-page";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){

        return "employees/access-denied";
    }
}
