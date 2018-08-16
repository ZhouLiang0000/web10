package com.ai.java.web.jdbc.test;

import com.ai.java.web.jdbc.utils.JDBCUtils_V3;
import com.ai.java.web.jdbc.MyDataSource;
import com.ai.java.web.jdbc.MyDataSource1;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestDataSource {
    //使用未改造的Connection对象
    @Test
    public void testInsertUser() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1、创建连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            //2、获取连接对象
            conn = dataSource.getConnection();
            //3、编写sql语句
            String insertSql = "insert into tbl_user values(null,?,?)";
            //4、获取执行sql语句对象
            pstmt = conn.prepareStatement(insertSql);
            //5、设置参数
            pstmt.setString(1, "吕布");
            pstmt.setString(2, "貂蝉");
            //6、执行sql语句
            int row = pstmt.executeUpdate();
            if (row > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataSource.backConnection(conn);
        }
    }

    //使用改造的Connection对象
    @Test
    public void testInsertUser1() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1、创建自定义连接池对象
        DataSource dataSource = new MyDataSource1();
        try {
            //2、获取连接对象
            conn = dataSource.getConnection();
            //3、编写sql语句
            String insertSql = "insert into tbl_user values(null,?,?)";
            //4、获取执行sql语句对象，必须在自定义的Connection中重写prepareStatement(sql)方法，否则报null指针
            pstmt = conn.prepareStatement(insertSql);
            //5、设置参数
            pstmt.setString(1, "吕布2");
            pstmt.setString(2, "貂蝉2");
            //6、执行sql语句
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
}
