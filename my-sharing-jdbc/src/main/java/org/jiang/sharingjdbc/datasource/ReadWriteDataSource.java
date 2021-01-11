package org.jiang.sharingjdbc.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadWriteDataSource {

    private static DataSource dataSource;

    public static DataSource getInstance() {
        if (dataSource != null) {
            return dataSource;
        }
        try {
            return create();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static DataSource create() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第 1 个数据源
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        masterDataSource.setUrl("jdbc:mysql://192.168.232.130:3306/shiro_test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("root");
        dataSourceMap.put("master", masterDataSource);
        // 配置第 2 个数据源
        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        slaveDataSource.setUrl("jdbc:mysql://192.168.232.132:3306/shiro_test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8");
        slaveDataSource.setUsername("root");
        slaveDataSource.setPassword("root");
        dataSourceMap.put("slave", slaveDataSource);
        //创建主从复制数据源
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new
                MasterSlaveRuleConfiguration("masterSlaveDataSource", "master",
                Arrays.asList("slave"));
        dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap,
                masterSlaveRuleConfig, new Properties());
        //返回数据源
        return dataSource;
    }
}
