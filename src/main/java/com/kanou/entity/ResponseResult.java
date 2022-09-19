package com.kanou.entity;

import java.io.Serializable;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 10:44
 */
public class ResponseResult<T> implements Serializable {
    /** 响应状态码*/
    private Integer code;

    /** 数据*/
    private T result;

    /** 数据数量*/
    private Integer count;

    public ResponseResult(Integer code, T result, Integer count) {
        this.code = code;
        this.result = result;
        this.count = count;
    }
}
