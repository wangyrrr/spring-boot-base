package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一异常定义
 * @Author: WangYuanrong
 * @Date: 2021/4/7 11:04
 */
@Getter
@Setter
public class ApiException extends RuntimeException {

    private Integer code;
    private String message;

    public ApiException(String message) {
        this.code = 500;
        this.message = message;
    }

    public ApiException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
