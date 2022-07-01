package com.kanou.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Jackson工具类
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/7/1 16:26
 */
public class JacksonUtil {

    static ObjectMapper mapper = new ObjectMapper();

    static Log log= LogFactory.getLog(WebSocketServer.class);

    /**
     * json字符串转成list
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> jsonToList(@NonNull String jsonString, Class<T> cls) {
        try {
            return mapper.readValue(jsonString, getCollectionType(List.class, cls));
        } catch (JsonProcessingException e) {
            String className = cls.getSimpleName();
            log.error("Jackson转换错误", e);
        }
        return null;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  实体bean
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
