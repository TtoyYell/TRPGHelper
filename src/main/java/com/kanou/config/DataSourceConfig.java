package com.kanou.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Durid配置类
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/5/24 21:29
 */
@Configuration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource getDataSource(){
        return new DruidDataSource();
    }

/**
 * 配置监控服务器
 * @return 返回监控注册的servlet对象
 * @author Ye Tianyi
 */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","root");
        initParameters.put("loginPassword","y@cp3winer");
        initParameters.put("allow","");
        bean.setInitParameters(initParameters);
        return bean;
    }

/**
 * 过滤器
 * @return 返回Filter对象
 * @author Ye Tianyi
 */
    @Bean
    public FilterRegistrationBean filterRegistrationBean (){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        HashMap<String, String> InitParameters = new HashMap<>();
        InitParameters.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.js");
        bean.setInitParameters(InitParameters);
        return bean;
    }

}
