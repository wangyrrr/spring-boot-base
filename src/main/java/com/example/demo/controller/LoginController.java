package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.ApiException;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.User;
import com.example.demo.sesrvice.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/29 14:51
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private SecretKey key;

//    private static final SecretKey KEY = Keys.hmacShaKeyFor("2162d3e65a421bc0ac76ae5acfe29c650becb73f2a9b8ce57941036331b1aaa8".getBytes(StandardCharsets.UTF_8));

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginDto dto) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getUsername()));
        if (user == null || !dto.getPassword().equals(user.getPassword())) {
            throw new ApiException("用户名或密码错误");
        }

        String token = Jwts.builder().setSubject("token").claim("type", "account").claim("userId", user.getId())
                .signWith(key).setIssuedAt(new Date()).compact();
        ModelAndView modelAndView = new ModelAndView();
        return token;
    }



    public static void main(String[] args) {

        SecretKey key = Keys.hmacShaKeyFor("2162d3e65a421bc0ac76ae5acfe29c650becb73f2a9b8ce57941036331b1aaa8".getBytes(StandardCharsets.UTF_8));
        String jws = Jwts.builder().setSubject("token").claim("type", "account").claim("userId", "1")
                .signWith(key).setIssuedAt(new Date()).compact();
        System.out.println(jws);
        final Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody();
        System.out.println(body);
    }
}
