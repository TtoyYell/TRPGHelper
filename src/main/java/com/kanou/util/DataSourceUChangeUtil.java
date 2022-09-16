package com.kanou.util;

/**
 * 切换数据源工具类
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/16 14:16
 */
public class DataSourceUChangeUtil {

    /** 默认数据源 */
    public static final String DEFAULT_DATASOURCE = "DB1";

    /** 采用ThreadLocal保证多线程下 线程隔离*/
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dbType
     * */
    public static void setDb(String dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源名字
     * */
    public static String getDb(){
        return contextHolder.get();
    }

}
