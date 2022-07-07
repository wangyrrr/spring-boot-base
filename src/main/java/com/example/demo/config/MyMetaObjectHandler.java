package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.demo.common.Constant;
import com.example.demo.dto.LoginUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 填充创建人和更新人
 * @Author: WangYuanrong
 * @Date: 2021/6/18 17:14
 */
//@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private HttpServletRequest request;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", Long.class, this.getLoginUser());
        this.strictInsertFill(metaObject, "createUser", String.class, this.getLoginUserName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateBy", Long.class, this.getLoginUser());
    }


    private LoginUserDTO loginUser() {
        try {
            if (request != null) {
                final Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER);
                if (attribute != null) {
                    return  (LoginUserDTO) attribute;
                }
                String token = request.getHeader("Authorization");
                // todo 根据token获取用户信息
                String json = "";
                if (StringUtils.isNotBlank(json)){
                    final LoginUserDTO loginUser = JSON.parseObject(json, LoginUserDTO.class);
                    request.getSession().setAttribute(Constant.LOGIN_USER, loginUser);
                    return loginUser;
                }
            }
        } catch (Exception e) {
            log.warn("元数据填充获取登录用户异常", e);
        }
        return null;
    }

    private Long getLoginUser() {
        final LoginUserDTO loginUser = loginUser();
        if (loginUser != null) {
            return loginUser.getUserId();
        }
        return null;
    }

    private String getLoginUserName() {
        final LoginUserDTO loginUser = loginUser();
        if (loginUser != null) {
            return loginUser.getUserName();
        }
        return null;
    }

}
