package se.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {

    @Test
    public void demo1() throws SQLException, ClassNotFoundException {
        Connection connection = getPostgresql();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery("select * from user");
        final ResultSetMetaData metaData = resultSet.getMetaData();
        final int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(metaData.getColumnName(i));
        }
        System.out.println(connection);
    }

    public Connection getMysql() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://49.233.146.217:3306/hpm_dev?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String user = "root";
        String password = "hand123!";
        return DriverManager.getConnection(url, user, password);
    }

    public Connection getDM() throws SQLException, ClassNotFoundException {
        Class.forName("dm.jdbc.driver.DmDriver");
        String url = "jdbc:dm://192.168.1.125:5236/PROD";
        String user = "SYSDBA";
        String password = "SYSDBA";
        return DriverManager.getConnection(url, user, password);
    }

    public Connection getPostgresql() throws SQLException, ClassNotFoundException {
        //Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://127.0.0.1:5432/kmj";
        String user = "postgres";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }

}
