package com.eij.wenjuan.component.contants;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public enum ResponseCode {
    SUCCESS(0, ""),

    AUTH_OWNER(-10, "无操作权限"),

    UNKNOWN(-2, "未知");
    private int code;

    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResponseCode parseCode(int code) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.code == code) {
                return responseCode;
            }
        }
        return UNKNOWN;
    }
}

