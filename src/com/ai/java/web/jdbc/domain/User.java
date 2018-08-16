package com.ai.java.web.jdbc.domain;

import java.io.Serializable;

/**
 * Author：zhouliang
 * Date：2018/8/16:15:35
 * Email：18510971680@163.com
 * Description：用户类
 */
public class User implements Serializable {
    private int uid;
    private String uname;
    private String upassword;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                '}';
    }
}
