package extra.druid;

import cn.hutool.core.lang.Assert;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcUtils;

import java.sql.*;
import java.util.*;
import java.util.function.Function;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2021/1/10 14:10
 */

public class DruidJdbcExecutor {

    private DruidJdbcExecutor() {
    }

    private static class DruidJdbcExecutorHolder {

        private static final DruidDataSource dataSource = new DruidDataSource();

        static {
            dataSource.setUrl("jdbc:mysql://192.168.1.143:3306/v5_gdc4_01");
            dataSource.setUsername("gdc4");
            dataSource.setPassword("aisWvdS4");

            dataSource.setInitialSize(3);
            dataSource.setMaxActive(20);
            dataSource.setMinIdle(3);

            dataSource.setTimeBetweenEvictionRunsMillis(60000);
            dataSource.setMinEvictableIdleTimeMillis(300000);

            dataSource.setValidationQuery("select 'x'");
            dataSource.setTestWhileIdle(true);
            dataSource.setTestOnBorrow(false);
            dataSource.setTestOnReturn(false);

            dataSource.setPoolPreparedStatements(true);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        }

        public static DruidDataSource jdbc(String url, String userName, String password) {
            dataSource.setUrl(url);
            dataSource.setUsername(userName);
            dataSource.setPassword(password);
            return dataSource;
        }
    }

    public static <T> List<T> executeQuery(Function<ResultSet, T> fun, String sql, Object... parameters) throws SQLException {
        return executeQuery(fun, sql, Arrays.asList(parameters));
    }

    public static List<Map<String, Object>> executeSql(String sql, Object... parameters) throws SQLException {
        return executeSql(sql, Arrays.asList(parameters));
    }

    private static <T> List<T> executeQuery(Function<ResultSet, T> fun, String sql, List<Object> parameters) throws SQLException {
        Assert.notNull(fun, "转换函数不能为空");
        List<T> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            setParameters(stmt, parameters);
            rs = stmt.executeQuery();
            while (rs.next()) {
                T object = fun.apply(rs);
                result.add(object);
            }
            return result;
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
            JdbcUtils.close(conn);
        }
    }

    private static List<Map<String, Object>> executeSql(String sql, List<Object> parameters) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            setParameters(stmt, parameters);
            rs = stmt.executeQuery();
            ResultSetMetaData rsMeta = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 0, size = rsMeta.getColumnCount(); i < size; ++i) {
                    String columName = rsMeta.getColumnLabel(i + 1);
                    Object value = rs.getObject(i + 1);
                    row.put(columName, value);
                }
                result.add(row);
            }
            return result;
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
            JdbcUtils.close(conn);
        }
    }

    private static DruidDataSource getDataSource() {
        return DruidJdbcExecutorHolder.dataSource;
    }

    private static void setParameters(PreparedStatement stmt, List<Object> parameters) throws SQLException {
        for (int i = 0, size = parameters.size(); i < size; ++i) {
            Object param = parameters.get(i);
            stmt.setObject(i + 1, param);
        }
    }
}
