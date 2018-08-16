package com.ai.java.web.jdbc.utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Author：zhouliang
 * Date：2018/8/16:14:23
 * Email：18510971680@163.com
 * Description：DBCP工具类
 */
public class DBCPUtils {
    public static DataSource dataSource = null;

    static {
        try {
            //1、加载properties文件获取输入流
            InputStream is = DBCPUtils.class.getClassLoader().getResourceAsStream("db.properties");
            //2、创建properties对象加载输入流
            Properties props = new Properties();
            props.load(is);
            //3、获取dataSource对象
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description 获取数据源
     * @Return javax.sql.DataSource
     * @Author zhouliang
     * @Date 2018/8/16 14:32
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * @Description 获取连接对象
     * @Return java.sql.Connection
     * @Author zhouliang
     * @Date 2018/8/16 14:34
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
