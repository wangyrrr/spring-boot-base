package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户数据对象
 * @Author: WangYuanrong
 * @Date: 2022/4/8 14:54
 */
@Data
public class LoginUserDTO implements Serializable {

    private Long userId;
}