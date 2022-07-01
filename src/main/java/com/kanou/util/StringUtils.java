package com.kanou.util;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/7/1 16:43
 */
public class StringUtils {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
