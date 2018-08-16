package com.ai.java.web.jdbc.test;

import com.ai.java.web.jdbc.utils.JDBCUtils_V3;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 测试工具类
 */
public class TestUtlis {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    @Test
    public void testUpdateById(){
        try {
            //1、获取连接
            conn = JDBCUtils_V3.getConnection();
            //2、编写sql语句
            String updateSql = "update tbl_user set upassword = ? where uid = ?";
            //3、获取执行sql语句对象
            pstmt = conn.prepareStatement(updateSql);
            //4、设置参数
            pstmt.setString(1,"wangwu");
            pstmt.setInt(2,6);
            //5、执行sql语句
            int row = pstmt.executeUpdate();
            if(row >= 0){
                System.out.println("更新成功");
            }else{
                System.out.println("更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //6、释放资源
            JDBCUtils_V3.release(conn,pstmt,null);
        }
    }

    @Test
    public void testDeleteById() {
        try {
            //1、获取连接
            conn = JDBCUtils_V3.getConnection();
            //2、编写sql语句
            String deleteSql = "delete from tbl_user where uid = ?";
            //3、获取执行sql语句的对象
            pstmt = conn.prepareStatement(deleteSql);
            //4、设置参数
            pstmt.setInt(1, 4);
            //5、执行sql语句
            int row = pstmt.executeUpdate();
            if (row >= 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
            JDBCUtils_V3.release(conn, pstmt, null);
        }
    }


    @Test
    public void testInsert() {
        try {
            //1、获取连接
            conn = JDBCUtils_V3.getConnection();
            //2、编写sql语句
            String insertSql = "insert into tbl_user values(null,?,?)";
            //3、获取执行sql语句对象
            pstmt = conn.prepareStatement(insertSql);
            //4、设置参数
            pstmt.setString(1, "lisi");
            pstmt.setString(2, "wangwu");
            //5、执行插入操作
            int row = pstmt.executeUpdate();
            if (row >= 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
            JDBCUtils_V3.release(conn, pstmt, null);
        }
    }


    @Test
    public void testFindUserById() {
        try {
            //1、获取连接
            conn = JDBCUtils_V3.getConnection();
            //2、编写sql语句
            String sql = "select * from tbl_user where uid=?";
            //3、获取执行语句对象
            pstmt = conn.prepareStatement(sql);
            //4、设置参数
            pstmt.setInt(1, 2);
            //5、执行查询操作
            rs = pstmt.executeQuery();
            //6、处理结果集
            while (rs.next()) {
                System.out.println(rs.getString(2) + "-----" + rs.getString("upassword"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            JDBCUtils_V3.release(conn, pstmt, rs);
        }
    }
}
