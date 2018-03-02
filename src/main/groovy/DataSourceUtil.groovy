import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * Created by Lzx on 2017/10/17.
 */
public class DataSourceUtil {

    private static final String URL_PREFIX = "jdbc:mysql://localhost:3306/";

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "";

    static String dbName = 'brush'
    static String dbServer = '127.0.0.1:5432'
    static String dbUser = "brush"
    static String dbPassword = "brush008"

    public static DataSource createDataSource(final String dataSourceName) {
        DruidDataSource dds = new DruidDataSource();
        dds.url = "jdbc:postgresql://$dbServer/$dbName";
        dds.driverClassName = "org.postgresql.Driver";
        dds.initialSize = 1;
        dds.maxActive = 30;
        dds.minIdle = 1;
        dds.poolPreparedStatements = true;
        dds.username = dbUser;
        dds.password = dbPassword;
        dds.validationQuery = "SELECT 1";
        dds.testWhileIdle = true;
        dds.testOnBorrow = false;
        dds.testOnReturn = false;
        //配置获取连接等待超时的时间
        dds.maxWait = 60000;
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dds.timeBetweenEvictionRunsMillis = 60000;
        //配置一个连接在池中最小生存的时间，单位是毫秒
        dds.minEvictableIdleTimeMillis = 300000;
        //配置监控统计拦截的filters
//        dds.filters = "stat"
//        //对于建立连接过长的连接强制关闭
//        dds.removeAbandoned = true
//        //如果连接建立时间超过了30分钟，则强制将其关闭
//        dds.removeAbandonedTimeout = 1800
//        //将当前关闭动作记录到日志
//        dds.logAbandoned = true

        return dds;
    }
}
