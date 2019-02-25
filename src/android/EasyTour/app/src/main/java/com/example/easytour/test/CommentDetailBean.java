package com.example.easytour.test;

import java.util.List;

/**
 * Created by 黄家慧 on 2018/12/20.
 */

public class CommentDetailBean {
    private int id;
    private String userid;         //userid
    private String author;    //author
    private String data;     //内容data
    private int pinglun;     //评论量 pinglun
    private int zan;            //点赞数
    private String where;       //对某地内容
    private List<ReplyDetailBean> replyList;

    public CommentDetailBean(String author,  String data, String where) {
        this.author = author;
        this.data = data;
        this.where = where;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPinglun() {
        return pinglun;
    }

    public void setPinglun(int pinglun) {
        this.pinglun = pinglun;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    public String getWhere() {
        return where;
    }

    public void setReplyList(List<ReplyDetailBean> replyList) {
        this.replyList = replyList;
    }
    public List<ReplyDetailBean> getReplyList() {
        return replyList;
    }
}
