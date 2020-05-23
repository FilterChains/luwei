package com.luwei.supermarket.util;

import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface SuperFunction<T, R> extends Function<T, R>, Serializable {
}
