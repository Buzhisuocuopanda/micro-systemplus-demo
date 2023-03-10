package com.mkst.mini.systemplus.common.base;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(0),//成功
    FAIL(400),//失败
    TOKEN_FAIL(1),//token失效
    BIND_USER(2),//用户未绑定
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    public int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    ResultCode(int code) {
        this.code = code;
    }
}
