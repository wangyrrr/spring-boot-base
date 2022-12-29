package com.example.demo.controller;

import com.example.demo.es.Consumer;
import com.example.demo.es.ConsumerRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: WangYuanrong
 * @Date: 2022/4/8 11:15
 */
@RestController
@RequestMapping("/test")
@Slf4j
@Api(tags = "测试接口")
public class TestController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/openId")
    public Consumer openId(String openId) {
        final Consumer consumer = consumerRepository.findByOpenId(openId);
        return consumer;
    }

    @GetMapping("/likeOpenId")
    public List<Consumer> likeOpenId(String openId) {
        return consumerRepository.findByOpenIdLike(openId);
    }



    @GetMapping("/mobile")
    public List<Consumer> mobile(String mobile) {
        return consumerRepository.findByMobile(mobile);
    }

    @GetMapping("/query")
    public List<Consumer> query(String mobile) {
        return null;
    }

    @GetMapping("delete")
    public Boolean delete(String openId) {
        consumerRepository.deleteByOpenId(openId);
        return Boolean.TRUE;
    }

    @GetMapping("/insert")
    public Boolean insert() {
        Consumer consumer = new Consumer();
        consumer.setId(111L);
        consumer.setOpenId("xxx");
        consumer.setMobile("13111111111");
        consumerRepository.save(consumer);
        return true;
    }
}
