package com.moderndev.taskmanager.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClassUtils {

    protected ClassUtils() { }

    public static <T> void  setIfNotNull(final Supplier getter, final Consumer setter) {

        T t = (T) getter.get();

        if (null != t) {
            setter.accept(t);
        }
    }
}
