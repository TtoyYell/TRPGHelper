package com.kanou;

import com.kanou.entity.CocRole;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class TrpgHelperApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(TrpgHelperApplication.class, args);


        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String,Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.entrySet().stream().filter(e -> e.getKey().startsWith("createRoleController"))
            .forEach(e -> {
                System.out.println(e.getKey() +"="+e.getValue());
            });

        List<CocRole> list = new ArrayList<>();
        list.add(new CocRole("10","张一","张一",10,"张一","男"));
        list.add(new CocRole("10","张一","张一",10,"张一","男"));
        list.add(new CocRole("10","张二","张二",18,"张二","男"));
        list.add(new CocRole("10","张二","张二",18,"张二","男"));
        list.stream().collect(Collectors.groupingBy(CocRole::getPcName, Collectors.averagingInt(CocRole::getAge)))
                .forEach((k,v)-> System.out.println(k +"="+v));
    }

}
