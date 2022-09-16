package com.kanou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class TrpgHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrpgHelperApplication.class, args);
    }

}
