package com.example.easytour.test;

/**
 * Created by 黄家慧 on 2018/12/20.
 */

public class ReplyDetailBean {

    private String username;    //评论的人 username
    private String userid;             //评论人的id    userid
    private int tipid;   //评论的id tipid
    private String content;     //评论的内容 content
    private String status;      //评论状态 status
    private String time;  //评论时间 time
    public ReplyDetailBean(String content){
        this.content = content;
    }
    public ReplyDetailBean(String username, String userid, int tipid, String content, String status, String time) {
        this.username = username;
        this.userid = userid;
        this.tipid = tipid;
        this.content = content;
        this.status = status;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getTipid() {
        return tipid;
    }

    public void setTipid(int tipid) {
        this.tipid = tipid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

}
