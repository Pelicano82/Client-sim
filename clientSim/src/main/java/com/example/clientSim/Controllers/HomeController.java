package com.example.clientSim.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "landing";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

    @GetMapping("/finepay")
    public String finepay() {
        return "finepay";
    }

    @GetMapping("/paymentconfirmation")
    public String paymentconfirmation() {
        return "paymentconfirmation";
    }
}
