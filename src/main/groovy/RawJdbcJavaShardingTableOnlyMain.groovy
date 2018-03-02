import io.shardingjdbc.core.api.ShardingDataSourceFactory
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration
import io.shardingjdbc.core.api.config.TableRuleConfiguration

import javax.sql.DataSource
import java.sql.SQLException

/**
 * Created by Lzx on 2017/10/17.
 */
final class RawJdbcJavaShardingTableOnlyMain {

    // CHECKSTYLE:OFF
    public static void main(final String[] args) throws SQLException {
        // CHECKSTYLE:ON
        new RawJdbcRepository(getShardingDataSource()).demo();
    }

    private static DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig);
    }

    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds_jdbc.t_order_\${[0, 1]}".toString());
        orderTableRuleConfig.setKeyGeneratorColumnName("order_id");
        return orderTableRuleConfig;
    }

    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
        orderItemTableRuleConfig.setLogicTable("t_order_item");
        orderItemTableRuleConfig.setActualDataNodes("ds_jdbc.t_order_item_\${[0, 1]}".toString());
        return orderItemTableRuleConfig;
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(1, 1);
        result.put("ds_jdbc", DataSourceUtil.createDataSource("ds_jdbc"));
        return result;
    }
}
