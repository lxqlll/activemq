package com.lxq.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: lxq
 * @createDate: 2020/11/14 0014
 * @version: 1.0
 */
@Setter
@Getter
public class Result<T> {
    //返回数据
    private T data;
    //返回码
    private String code;
    //返回success
    private Boolean success;
    // 返回描述
    private String msg;

    public Result<T> setCode(ResultEnum ResultEnum) {
        this.code = ResultEnum.getCode();
        return this;
    }

    public String getCode() {
        return code;
    }

    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Result<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
