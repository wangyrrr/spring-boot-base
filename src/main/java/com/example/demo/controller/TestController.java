package com.example.demo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangYuanrong
 * @Date: 2022/4/8 11:15
 */
@RestController
@RequestMapping("/test")
@Slf4j
@Api(tags = "测试接口")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
