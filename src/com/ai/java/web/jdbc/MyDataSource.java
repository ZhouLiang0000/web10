package com.ai.java.web.jdbc;

import com.ai.java.web.jdbc.utils.JDBCUtils_V3;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.logging.Logger;
/**
 * 使用Connection创建的DataSource
 */
public class MyDataSource implements javax.sql.DataSource {
    //1、创建一个容器，用于存储Connection对象
    private static LinkedList<Connection> pool = new LinkedList<>();

    //2、创建5个连接放到容器中
    static {
        for (int i = 0; i < 5; i++) {
            Connection conn = JDBCUtils_V3.getConnection();
            pool.add(conn);
        }
    }

    //重写获取连接的操作
    @Override
    public Connection getConnection() {
        Connection conn = null;
        //3、使用前判断
        if (pool.size() == 0) {
            //4、池子里面没有，在创建一些
            for (int i = 0; i < 5; i++) {
                conn = JDBCUtils_V3.getConnection();
                pool.add(conn);
            }
        }
        //5、从池子里面获取连接对象
        conn = pool.remove(0);
        return conn;
    }

    /**
     * 归还连接对象到链接池
     **/
    public void backConnection(Connection conn) {
        pool.add(conn);
    }

    @Override
    public Connection getConnection(String username, String password) {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) {

    }

    @Override
    public int getLoginTimeout() {
        return 0;
    }

    @Override
    public void setLoginTimeout(int seconds) {

    }

    @Override
    public Logger getParentLogger() {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }
}
