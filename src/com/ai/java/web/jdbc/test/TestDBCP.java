package com.ai.java.web.jdbc.test;

import com.ai.java.web.jdbc.utils.JDBCUtils_V3;
import com.ai.java.web.jdbc.utils.DBCPUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Author：zhouliang
 * Date：2018/8/16:14:34
 * Email：18510971680@163.com
 * Description：DBCP测试类
 */
public class TestDBCP {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    @Test
    public void testUpdateUserById() {
        try {
            //1、获取连接对象
            conn = DBCPUtils.getConnection();
            //2、编写sql语句
            String updateSql = "update tbl_user set upassword = ? where uid = ?";
            //3、获取执行sql语句的对象
            pstmt = conn.prepareStatement(updateSql);
            //4、设置参数
            pstmt.setString(1, "lvbu");
            pstmt.setInt(2, 9);
            //5、执行sql语句
            int row = pstmt.executeUpdate();
            if (row > 0) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_V3.release(conn, pstmt, null);
        }
    }

}
