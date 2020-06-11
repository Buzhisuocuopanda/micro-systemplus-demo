package com.mkst.mini.systemplus.common.shiro.session;

import com.mkst.mini.systemplus.common.shiro.utils.LoginType;
import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken {

    private LoginType type;//表示当前登录的类型

    public UserToken() {
        super();
    }
    public UserToken(String username, String password, LoginType type, boolean rememberMe) {
        super(username, password, rememberMe);
        this.type = type;
    }
    /**免密登录*/
    public UserToken(String username, LoginType type) {
        super(username, "", true, null);
        this.type = type;
    }
    /**账号密码登录*/
    public UserToken(String username, String password) {
        super(username, password, false, null);
        this.type = LoginType.PASSWORD;
    }
    public LoginType getType() {
        return type;
    }
    public void setType(LoginType type) {
        this.type = type;
    }
}
