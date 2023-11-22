package com.fastcampus.corona.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
//@RequestMapping("/api")
//@RestController
public class APIAuthController {

    @GetMapping("/sign-up")
    public String signUp() {
        return "Done.";
    }

    @GetMapping("/login")
    public String login() {
        return "Done.";
    }
}
