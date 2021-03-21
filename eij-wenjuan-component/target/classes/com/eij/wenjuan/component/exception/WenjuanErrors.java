package com.eij.wenjuan.component.exception;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-13
 */
public enum WenjuanErrors implements ErrorCode {

    OPERATION_FAIL(12, "操作失败"),
    NOT_FOUND(13, "不存在"),
    EXIST_ALREADY(14, "已存在"),
    ;

    private final int code;

    private final String message;

    WenjuanErrors(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
