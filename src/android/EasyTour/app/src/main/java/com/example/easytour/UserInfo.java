package com.example.easytour;

import java.io.Serializable;

/**
 * Created by 黄家慧 on 2018/12/12.
 */

public class UserInfo implements Serializable{
    String userid;
    String password;
    String username;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!userid.equals(userInfo.userid)) return false;
        if (!password.equals(userInfo.password)) return false;
        return username.equals(userInfo.username);

    }

    @Override
    public int hashCode() {
        int result = userid.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfo() {
    }

    public UserInfo(String userid, String password, String username) {
        this.userid = userid;
        this.password = password;
        this.username = username;
    }
}
