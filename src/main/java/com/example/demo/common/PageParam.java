package com.example.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页查询入参
 * @Author: WangYuanrong
 * @Date: 2022/1/27 14:34
 */
@ApiModel(value = "分页查询入参")
@Data
public class PageParam {

    /**
     * 当前页，从1开始
     */
    @ApiModelProperty(value = "当前页，从1开始")
    protected int page = 1;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    protected int limit = 10;

}
