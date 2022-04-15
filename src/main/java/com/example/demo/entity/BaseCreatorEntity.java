package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 带创建者更新者实体类
 * @Author: WangYuanrong
 * @Date: 2022/4/15 16:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseCreatorEntity extends BaseEntity {

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;


}
