package com.example.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询入参
 * @Author: WangYuanrong
 * @Date: 2022/1/27 14:34
 */
@ApiModel(value = "分页查询入参")
public class PageParam {

    @ApiModelProperty(value = "当前页，从1开始")
    private int current;

    @ApiModelProperty(value = "每页条数")
    private int size = 10;

}
