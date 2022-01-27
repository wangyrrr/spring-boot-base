package com.example.demo.common;

import com.example.demo.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一结果返回定义
 * @Author: WangYuanrong
 * @Date: 2021/4/7 11:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回实体")
public class Result<T> {

    @ApiModelProperty(value = "提示信息")
    private String msg;

    @ApiModelProperty(value = "状态码，200表示成功，非200表示错误，提示用户返回的提示信息")
    private Integer code;

    @ApiModelProperty(value = "响应数据")
    private T data;


    public static <T> Result<T> response() {
        Result<T> res = new Result<>();
        res.setCode(ResultCodeEnum.SUCCESS.getCode());
        res.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        return res;
    }

    /**
     * 根据消息码等生成接口返回对象
     *
     * @param data 数据对象
     * @param <T>
     * @return
     */
    public static <T> Result<T> response(T data) {
        Result<T> res = new Result<>();
        res.setCode(ResultCodeEnum.SUCCESS.getCode());
        res.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        res.setData(data);
        return res;
    }

    /**
     * 根据消息码等生成接口返回对象
     *
     * @param resultCodeEnum 结果返回码
     * @param data           数据对象
     * @param <T>
     * @return
     */
    public static <T> Result<T> response(ResultCodeEnum resultCodeEnum, T data) {
        Result<T> res = new Result<>();
        res.setCode(resultCodeEnum.getCode());
        res.setMsg(resultCodeEnum.getMsg());
        res.setData(data);
        return res;
    }

    public static <T> Result<T> response(ResultCodeEnum resultCodeEnum) {
        Result<T> res = new Result<>();
        res.setCode(resultCodeEnum.getCode());
        res.setMsg(resultCodeEnum.getMsg());
        return res;
    }

    /**
     * 根据消息码等生成接口返回对象
     *
     * @param code 结果返回码
     * @param msg  结果返回消息
     * @param data 数据对象
     * @param <T>
     * @return
     */
    public static <T> Result<T> response(Integer code, String msg, T data) {
        Result<T> res = new Result<>();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    /**
     * 根据消息码等生成接口返回对象
     *
     * @param code 结果返回码
     * @param msg  结果返回消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> exception(Integer code, String msg) {
        Result<T> res = new Result<>();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

    /**
     * 请求异常返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> exception() {
        return response(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMsg(), null);
    }

}
