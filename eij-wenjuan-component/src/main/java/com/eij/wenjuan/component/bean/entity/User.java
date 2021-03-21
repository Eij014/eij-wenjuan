package com.eij.wenjuan.component.bean.entity;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-13
 */
public class User {
    private int userId;

    private String username;

    private String password;

    private String phoneNumber;

    private String headUrl;

    private String createTme;

    private String userToken;

    public User() {

    }

    public User(String username, String password, String userToken) {
        this.username = username;
        this.password = password;
        this.userToken = userToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getCreateTme() {
        return createTme;
    }

    public void setCreateTme(String createTme) {
        this.createTme = createTme;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
