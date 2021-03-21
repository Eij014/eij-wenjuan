package com.eij.wenjuan.component.exception;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-13
 */
public class ServiceException extends RuntimeException {

    private final int errorCode;

    private final String errorMessage;

    public ServiceException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ServiceException ofError(ErrorCode errorCode) {
        return new ServiceException(errorCode.code(), errorCode.message());
    }

    public static ServiceException ofMessage(ErrorCode errorCode, String errorMessage) {
        return new ServiceException(errorCode.code(), errorMessage);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
