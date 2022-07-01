package com.kanou;

import com.kanou.entity.User;
import com.kanou.util.JacksonUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * JacksonUtil的测试类
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/7/1 16:30
 */
public class JacksonUtilTest {
    @Test
    public void testJson(){
        String json = "[" +
                "{\"userId\":\"1\",\"userName\":\"上海带刀沪卫\",\"password\":\"带刀大佬\"}" +
                ",{\"userId\":\"1\",\"userName\":\"上海辟谣专属队\",\"password\":\"职业辟谣，不信谣，不传谣，呵呵\"}" +
                "]";
        System.out.println(json);
        List lists = JacksonUtil.jsonToList(json, User.class);
        for (Object list : lists) {
            System.out.println(list);
        }
    }
}
