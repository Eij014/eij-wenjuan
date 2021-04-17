package com.eij.wenjuan.api.request;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-13
 */
public class UserLoginRequest {
    private String username;

    private String password;

    private String loginOrRegister;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginOrRegister() {
        return loginOrRegister;
    }

    public void setLoginOrRegister(String loginOrRegister) {
        this.loginOrRegister = loginOrRegister;
    }
}
