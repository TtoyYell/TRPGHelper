package com.kanou;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/8/5 19:29
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class randonTest {

    @Test
    public  void test() {
        Date date = new Date();
        Long seed = Long.parseLong(String.format("%tN", date));
        Random random = new Random(seed);
        List iList = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            System.out.println(random.nextInt(6) + 1);
        }
    }

}
