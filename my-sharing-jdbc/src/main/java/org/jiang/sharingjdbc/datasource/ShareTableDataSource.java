package org.jiang.sharingjdbc.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShareTableDataSource {

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
        dataSourceMap.put("ds0", masterDataSource);
        // 配置第 2 个数据源
        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        slaveDataSource.setUrl("jdbc:mysql://192.168.232.132:3306/shiro_test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8");
        slaveDataSource.setUsername("root");
        slaveDataSource.setPassword("root");
        dataSourceMap.put("ds1", slaveDataSource);

        // 配置 t_order 表规则
        TableRuleConfiguration orderTableRuleConfig = new
                TableRuleConfiguration("t_order", "ds${0..1}.t_order${0..1}");
        // 配置数据库表分片策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new
                InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new
                InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new
                ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
        // 创建数据源
        DataSource dataSource =
                ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig,
                        new Properties());
        //返回数据源
        return dataSource;
    }
}
