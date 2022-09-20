package com.kanou.entity;

import com.kanou.constant.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 10:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -7183336451033766632L;

    /** 响应状态码*/
    private Integer code;

    /** 数据*/
    private T result;

    /** 数据数量*/
    private Integer count;

    private String msg;

    public synchronized static <T> ResponseResult<T> setRes(ResponseCode responseCode){
        ResponseResult<T> responseResult = setRes(responseCode,null,0);
        return responseResult;
    }

    public synchronized static <T> ResponseResult<T> setRes(ResponseCode responseCode,T data){
        ResponseResult<T> responseResult = setRes(responseCode,data,1);
        return responseResult;
    }

    public synchronized static <T> ResponseResult<T> setRes(ResponseCode responseCode, T data, Integer count){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(responseCode.code);
        responseResult.setMsg(responseCode.msg);
        responseResult.setResult(data);
        responseResult.setCount(count);
        return responseResult;
    }

}
