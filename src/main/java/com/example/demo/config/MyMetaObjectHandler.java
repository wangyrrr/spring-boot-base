package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 填充创建人和更新人
 * @Author: WangYuanrong
 * @Date: 2021/6/18 17:14
 */
//@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", Long.class, this.getLoginUser());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateBy", Long.class, this.getLoginUser());
    }

    private Long getLoginUser() {
        try {
            return 0L;
        } catch (Exception e) {
            log.warn("元数据填充获取登录用户异常", e);
        }
        return null;
    }
}
