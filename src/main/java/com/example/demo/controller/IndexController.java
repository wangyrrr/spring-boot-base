package com.example.demo.controller;

import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private SecretKey key;

    @GetMapping
    @ResponseBody
    public Result<Boolean> domain() {
        return Result.ok();
    }

    @GetMapping({"/admin", "index"})
    public String index(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (StringUtils.isBlank(token)) {
//            return "login";
//        }
//        Claims body;
//        try {
//            body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            return "login";
//        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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
