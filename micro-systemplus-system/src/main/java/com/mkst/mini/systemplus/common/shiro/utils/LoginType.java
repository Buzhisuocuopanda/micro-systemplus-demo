package com.mkst.mini.systemplus.common.shiro.utils;

public enum LoginType {
    PASSWORD("password"), // 密码登录
    CAS("cas"),//cas认证
    OAUTH2("oauth2"),//oauth2认证
    WECHAT("wechat"), // 微信登录
    WECHAT_XCX("wechat_xcx"), // 微信小程序
    YZJ("yzj"), //云之家
    ISZ("isz"); // i深圳

    private String code;// 状态值

    private LoginType(String code) {
        this.code = code;
    }
    public String getCode () {
        return code;
    }
}
