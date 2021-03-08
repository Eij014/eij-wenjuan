package com.eij.wenjuan.component.bean;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public class UserContext {
    private String userName;
    private String email;
    private String phoneNumber;
    private String jSessionId;
    private String requestServerName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getjSessionId() {
        return jSessionId;
    }

    public void setjSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    public String getRequestServerName() {
        return requestServerName;
    }

    public void setRequestServerName(String requestServerName) {
        this.requestServerName = requestServerName;
    }
}
