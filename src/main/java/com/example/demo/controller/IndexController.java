package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping({"/admin", "index", "login"})
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home/home";
    }

    @GetMapping("/system/user")
    public String user() {
        return "system/user";
    }

    @GetMapping("/system/userAdd")
    public String userAdd() {
        return "system/userAdd";
    }

    @GetMapping("/system/userEdit")
    public String userEdit(@RequestParam Long id) {
        return "system/userEdit";
    }
}
