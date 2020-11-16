package com.lxq.result;

/**
 * @description:
 * @author: lxq
 * @createDate: 2020/11/14 0014
 * @version: 1.0
 */
public enum   ResultEnum {

    //这里是可以自己定义的，方便与前端交互即可
    ERROR("-1","失败"),
    NOT_TIME("-3","不在时间内"),
    REPEAT_REGISTER("-2", "该用户已注册"),
    NOT_FOUND_RESULT("-4","未找到结果集"),


    LOGIN_ERROR("401","用户名或者密码不正确"),
    UPDATE_ERROR("402","修改失败"),

    SUCCESS("200","成功"),
    NOT_FOUND("404","不存在该用户"),

    PASSWORD_ERROR("405","密码错误"),

    INTERNAL_SERVER_ERROR("500","服务器内部错误");

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
