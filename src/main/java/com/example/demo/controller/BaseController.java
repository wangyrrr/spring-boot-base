package com.example.demo.controller;

import com.example.demo.common.ApiException;
import com.example.demo.common.Constant;
import com.example.demo.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: WangYuanrong
 * @Date: 2021/6/18 17:56
 */
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    /**
     * 从session中获取登录用户，适用于spring session环境
     * @return
     */
    protected Long getUserId() {
        Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER);
        if (attribute == null) {
            throw new ApiException("无登录用户");
        }
        User user = (User) attribute;
        return user.getId();
    }


//    protected Long getUserId() {
//        return 1L;
//    }
}
