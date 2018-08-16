package com.ai.java.web.jdbc.test;

import com.ai.java.web.jdbc.utils.JDBCUtils_V3;
import com.ai.java.web.jdbc.utils.C3P0Utils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestC3P0 {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    @Test
    public void testInsertUser() {
        try {
            //1、获取连接
            conn = C3P0Utils.getConnection();
            //2、编写sql语句
            String insertSql = "insert into tbl_user values(null,?,?)";
            //3、获取执行sql语句的对象
            pstmt = conn.prepareStatement(insertSql);
            //4、设置参数
            pstmt.setString(1, "xiaoxiao");
            pstmt.setString(2, "xiaoxiao");
            //5、执行sql语句
            int row = pstmt.executeUpdate();
            if (row > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_V3.release(conn, pstmt, null);
        }
    }

    @Test
    public void testDeleteUser() {
        //加载默认配置
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //加载带名称的配置
        ComboPooledDataSource dataSource = new ComboPooledDataSource("com.ai.web");
        try {
            //1、获取连接
            conn = dataSource.getConnection();
            //2、编写sql语句
            String deleteSql = "delete from tbl_user where uid = ?";
            //3、获取执行sql的对象
            pstmt = conn.prepareStatement(deleteSql);
            //4、设置参数
            pstmt.setInt(1, 8);
            //5、执行sql语句
            int row = pstmt.executeUpdate();
            if (row > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_V3.release(conn, pstmt, null);
        }
    }
}
