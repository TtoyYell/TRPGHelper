package com.kanou;

import com.kanou.entity.CocRole;
import com.kanou.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author YeTianyi
 * @version 1.0
 * @date 2022/5/24 0:55
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DaoTest {

    @Autowired
    TestService service;

    @Test
    public void test() {
        CocRole cocRole = service.getCocRole();
        System.out.println(cocRole);
    }
}
