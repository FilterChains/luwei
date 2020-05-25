package com.luwei.supermarket.util;

import org.apache.commons.lang.StringUtils;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>@Description : 订单号生成器</p>
 * <p>@Author : luwei</p>
 * <p>@Date : 2019/10/24 0024 下午 15:24 </p>
 */
public final class ConvertUtils {

    private ConvertUtils() {
    }

    private static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    private static final String FORMAT_DATE = "yyyy-MM-dd";

    public static Integer toInt(String value, Integer defaultValue) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ne) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static int toInt(Integer value, int defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static int toInt(Double value, int defaultValue) {
        return null == value ? defaultValue : value.intValue();
    }

    public static Long toLong(String value, Long defaultValue) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException ne) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static Long toLong(Date value) {
        return toLong(value, null);
    }

    public static Long toLong(Date value, Long defaultValue) {
        return value == null ? defaultValue : value.getTime();
    }

    public static Double toDouble(String value, Double defaultValue) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException ne) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static Date toDate(String value, Date defaultValue, String format) {
        if (StringUtils.isNotBlank(value)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(value);
            } catch (ParseException pe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static Date toDate(String value, Date defaultValue) {
        return toDate(value, defaultValue, FORMAT_DATE);
    }

    public static Date toDate(Long value, Date defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return new Date(value);
    }

    public static Date toDate(Long value) {
        return value == null ? null : toDate(value, null);
    }

    public static Date toDateTime(String value, Date defaultValue) {
        return toDate(value, defaultValue, FORMAT_DATETIME);
    }

    public static String toString(Date value) {
        return toString(value, FORMAT_DATETIME);
    }

    public static String toString(Date value, String format) {
        if (value == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(value);
    }

    public static String toString(long value, String format) {
        return toString(new Date(value), format);
    }

    /**
     * <p>@Description : 获取函数参数名</p>
     * <p>@Author : luwei</p>
     * <p>@Date : 2019/11/6 0006 下午 17:04 </p>
     *
     * @param function
     * @return java.lang.String
     */
    public static <T> String toString(SuperFunction<T, ?> function) {
        try {
            Method method = function.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(function);
            String methodName = serializedLambda.getImplMethodName();
            if (methodName.startsWith("is")) {
                methodName = methodName.substring(2);
            } else {
                if (!methodName.startsWith("get") && !methodName.startsWith("set")) {
                    throw new IllegalArgumentException("Error parsing property name '" + methodName + "'.  Didn't start with 'is', 'get' or 'set'.");
                }
                methodName = methodName.substring(3);
            }
            if (methodName.length() == 1 || methodName.length() > 1 && !Character.isUpperCase(methodName.charAt(1))) {
                methodName = methodName.substring(0, 1).toLowerCase(Locale.ENGLISH) + methodName.substring(1);
            }
            return methodName;
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean toBoolean(Boolean value, boolean defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static <T extends Enum<T>> T toEnum(String value, Class<T> enumClass, T defaultValue) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return Enum.valueOf(enumClass, value);
            } catch (IllegalArgumentException ae) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

}
