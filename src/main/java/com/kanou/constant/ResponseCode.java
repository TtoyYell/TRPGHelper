package com.kanou.constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 13:54
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseCode {

    OK(200,"成功"),

    EMPTY(400,"参数为空");

    public Integer code;

    public String msg;
}
