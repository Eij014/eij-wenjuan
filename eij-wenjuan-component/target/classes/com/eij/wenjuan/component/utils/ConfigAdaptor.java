package com.eij.wenjuan.component.utils;

import java.util.function.Supplier;

import javax.annotation.Nullable;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-08
 */
public interface ConfigAdaptor<T> extends Supplier<T> {
    @Nullable
    T get();
}
