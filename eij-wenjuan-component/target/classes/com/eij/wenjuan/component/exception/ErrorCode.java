package com.eij.wenjuan.component.exception;

import javax.annotation.Nonnull;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-13
 */
public interface ErrorCode {
    int SUCCESS = 0;

    int code();

    @Nonnull
    String message();
}
