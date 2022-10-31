package com.kanou.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/31 20:52
 */
public class BeanCreateUtil {

    public static Object registerBean(ConfigurableApplicationContext applicationContext, Class clazz, String name, Map<String,Object> argsMap){

        if (applicationContext.containsBean(name)) {
            Object bean = applicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz)) {
                return bean;
            } else {
                throw new RuntimeException("重复的bean");
            }
        }

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        for (String key : argsMap.keySet()) {
            beanDefinitionBuilder.addPropertyValue(key,argsMap.get(key));
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanDefinitionRegistry.registerBeanDefinition(name,beanDefinition);
        return applicationContext.getBean(name,clazz);
    }

}
