package com.fastcampus.corona.controller.api;

import org.springframework.web.bind.annotation.GetMapping;

@Deprecated
//@RequestMapping("/api")
//@RestController
public class ApiAuthController {

    @GetMapping("/sign-up")
    public String signUp() {
        return "Done.";
    }

    @GetMapping("/login")
    public String login() {
        return "Done.";
    }
}
