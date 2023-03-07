package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.demo.common.ApiException;
import com.example.demo.common.Constant;
import com.example.demo.dto.LoginUserDTO;
import com.example.demo.entity.User;
import com.example.demo.enums.ResultCodeEnum;
import com.example.demo.sesrvice.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
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

    @Resource
    private SecretKey key;

    @Resource
    private IUserService userService;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", Long.class, this.getLoginUser());
        this.strictInsertFill(metaObject, "createUser", String.class, this.getLoginUserRealName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateBy", Long.class, this.getLoginUser());
    }


    private LoginUserDTO loginUser() {
        try {
            if (request != null) {
                final Object attribute = request.getAttribute(Constant.LOGIN_USER);
                if (attribute != null) {
                    return  (LoginUserDTO) attribute;
                }
                String token = request.getHeader("Authorization");
                if (StringUtils.isBlank(token)) {
                    throw new ApiException(ResultCodeEnum.UNAUTHORIZED);
                }
                Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
                Object userId = body.get("userId");
                User user = userService.getCache(Long.parseLong(userId.toString()));
                LoginUserDTO dto = new LoginUserDTO();
                dto.setUserId(user.getId());
                dto.setUsername(user.getUsername());
                dto.setRealName(user.getRealName());
                request.setAttribute(Constant.LOGIN_USER, dto);
                return dto;
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

    private String getLoginUserRealName() {
        final LoginUserDTO loginUser = loginUser();
        if (loginUser != null) {
            return loginUser.getRealName();
        }
        return null;
    }

}
