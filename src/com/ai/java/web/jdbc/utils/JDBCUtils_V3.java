package com.ai.java.web.jdbc.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 提供获取连接和释放资源的方法
 */
public class JDBCUtils_V3 {
    private static String driver = null;
    private static String url = null;
    private static String userName = null;
    private static String userPassword = null;

    //静态代码块加载配置文件信息
    static {
        try {
            //1、通过当前类获取类加载器
            ClassLoader classLoader = JDBCUtils_V3.class.getClassLoader();
            //2、通过类加载器获取输入流
            InputStream is = classLoader.getResourceAsStream("db.properties");
            //3、创建一个properties对象
            Properties props = new Properties();
            //4、加载输入流
            props.load(is);
            //5、获取值
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            userName = props.getProperty("username");
            userPassword = props.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接方法
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, userPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源的方法
     */
    public static void release(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
