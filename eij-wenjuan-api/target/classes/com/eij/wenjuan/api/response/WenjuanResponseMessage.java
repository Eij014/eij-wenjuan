package com.eij.wenjuan.api.response;

import java.util.Collections;
import java.util.Map;

import com.eij.wenjuan.component.bean.view.WenjuanResponseView;
import com.eij.wenjuan.component.contants.ResponseCode;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public class WenjuanResponseMessage<T> {
    @JsonView(WenjuanResponseView.BasicView.class)
    @ApiModelProperty(allowableValues = "0")
    private int code;

    @JsonView(WenjuanResponseView.BasicView.class)
    @ApiModelProperty
    private String message;

    @JsonView(WenjuanResponseView.BasicView.class)
    @ApiModelProperty
    private T data;

    /**
     * 无参构造函数，便于反序列化
     */
    public WenjuanResponseMessage() {

    }

    public WenjuanResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> WenjuanResponseMessage<T> success() {
        return new WenjuanResponseMessage<>(0, "", null);
    }

    public static <T> WenjuanResponseMessage<T> success(T data) {
        return new WenjuanResponseMessage<>(0, "", data);
    }

    public static <T> WenjuanResponseMessage<T> success(String msg, T data) {
        return new WenjuanResponseMessage<T>(0, msg, data);
    }

    public static <T> WenjuanResponseMessage<T> failure() {
        return new WenjuanResponseMessage<T>(-1, "failure", null);
    }

    public static <T> WenjuanResponseMessage<T> failure(String msg) {
        return new WenjuanResponseMessage<>(-1, msg, null);
    }

    public static <T> WenjuanResponseMessage<T> failure(int code, String msg) {
        return new WenjuanResponseMessage<>(code, msg, null);
    }

    public static <T> WenjuanResponseMessage<T> routeResponseMessage(int code) {
        if (code < 0) {
            ResponseCode responseCode = ResponseCode.parseCode(code);
            return WenjuanResponseMessage.failure();
        }
        Map<String, Integer> map = Collections.singletonMap("value", code);
        return new WenjuanResponseMessage<T>(0, "", (T) map);
    }

    public static <T> WenjuanResponseMessage<T> failure(int code, String msg, T data) {
        return new WenjuanResponseMessage<>(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
