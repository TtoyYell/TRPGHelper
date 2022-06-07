package com.kanou;

import com.alibaba.druid.pool.DruidDataSource;
import com.kanou.dao.CocRoleDao;
import com.kanou.entity.CocRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author YeTianyi
 * @version 1.0
 * @date 2022/5/24 0:55
 */
@SpringBootTest
public class DaoTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    CocRoleDao cocRoleDao;

    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        CocRole cocRole = cocRoleDao.getById(1);
        System.out.println(cocRole);
        connection.close();
    }
}
