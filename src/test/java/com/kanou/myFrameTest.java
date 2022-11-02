package com.kanou;

import com.kanou.DaoTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/11/2 15:19
 */
public class myFrameTest {

    @SneakyThrows
    @Test
    public void testAnnotation(){
        Annotation[] annotations = DaoTest.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType());
            if (annotation.annotationType().equals(SpringBootTest.class)) {
                System.out.println(((SpringBootTest)annotation).webEnvironment());
            }
        }
        if (DaoTest.class.isAnnotationPresent(SpringBootTest.class)) {
            System.out.println("存在");
        }
        // String 是不是 Object的子类或者子接口
        System.out.println(Object.class.isAssignableFrom(String.class));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(AbstractDependsOnBeanFactoryPostProcessor.class));

        Constructor<DaoTest> constructor = DaoTest.class.getConstructor();
        DaoTest daoTest = constructor.newInstance();
        System.out.println(daoTest);

    }

}
