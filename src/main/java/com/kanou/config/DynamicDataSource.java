package com.kanou.config;

import com.kanou.util.DataSourceUChangeUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/16 14:15
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceUChangeUtil.getDb();
    }
}
