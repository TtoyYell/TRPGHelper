package com.kanou.util;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/7/1 16:43
 */
public class StringUtils {

    public static boolean isBlank(Object str) {
        return str == null || String.valueOf(str).trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String valueOf(Object str) {
        if (isBlank(str))
            return "";
        else
            return String.valueOf(str);
    }
}
