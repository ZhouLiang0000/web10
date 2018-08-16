package com.ai.java.web.jdbc.test;

import com.ai.java.web.jdbc.domain.User;
import com.ai.java.web.jdbc.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Author：zhouliang
 * Date：2018/8/16:15:22
 * Email：18510971680@163.com
 * Description：DBUtils测试类：增删改查
 */
public class TestDBUtils {
    private List<Map<String, Object>> userMaps = null;
    private List<Object> userObjects = null;
    private List<User> users = null;
    private User user = null;

    public static void main(String[] args) {

    }

    @Test
    public void testQueryUserAll2() {
        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String querySql = "select * from tbl_user";
            userObjects = qr.query(querySql, new ColumnListHandler("uname"));
            for (Object object : userObjects) {
                System.out.println(object);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testQueryUserAll1() {
        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String querySql = "select * from tbl_user";
            userMaps = qr.query(querySql, new MapListHandler());
            for (Map<String, Object> userMap : userMaps) {
                System.out.println(userMap);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testQueryUserCount() {
        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String querySql = "select count(*) from tbl_user";
            long count = (long) qr.query(querySql, new ScalarHandler());
            System.out.println(count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testQueryUserById() {
        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String querySql = "select * from tbl_user where uid = ?";
            Object[] params = {10};
            user = qr.query(querySql, new BeanHandler<>(User.class), params);
            System.out.println(user.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testQueryAllUser() {
        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String queryAllSql = "select * from tbl_user";
            users = qr.query(queryAllSql, new BeanListHandler<>(User.class));
            for (User user : users) {
                System.out.println(user.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testUpdateUserById() {
        try {
            //1、创建核心类QueryRunner
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            //2、编写sql语句
            String updateSql = "update tbl_user set uname = ? where uid = ?";
            //3、设置参数
            Object[] params = {"hehe", 13};
            //4、执行sql语句
            int rows = qr.update(updateSql, params);
            if (rows > 0) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeleteUserById() {
        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String deleteSql = "delete from tbl_user where uid = ?";
            Object[] params = {13};
            int rows = qr.update(deleteSql, params);
            if (rows > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testInsertUser() {
        try {
            //1、创建核心类QueryRunner对象
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            //2、编写sql语句
            String insertSql = "insert into tbl_user values(null,?,?)";
            //3、设置参数
            Object[] params = {"cao", "cao"};
            //4、执行sql语句
            int rows = qr.update(insertSql, params);
            if (rows > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
