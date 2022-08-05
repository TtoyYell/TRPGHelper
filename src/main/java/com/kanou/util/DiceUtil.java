package com.kanou.util;

import java.util.Random;

/**
 * 骰子工具类
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/8/5 19:47
 */
public class DiceUtil {

    /**
     * 返回nd6 * 5 加 加值
     */
    public static String nD6mul5Plus (Random ran, int n ,int plus) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += d6(ran);
        }
        return String.valueOf(sum * 5 + plus);
    }

    /**
     * 返回nd6 * 5
     */
    public static String nD6mul5 (Random ran, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += d6(ran);
        }
        return String.valueOf(sum * 5);
    }

    /**
     * 获取d6
     * @return d6
     */
    public static int d6 (Random random) {
        return random.nextInt(6) + 1;
    }
}
