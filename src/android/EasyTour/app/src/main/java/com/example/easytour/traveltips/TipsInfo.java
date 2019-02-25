package com.example.easytour.traveltips;

import android.graphics.drawable.Drawable;

/**
 * Created by 黄家慧 on 2018/11/12.
 */

public class TipsInfo {
    int id;
    String author;
    String wherewant;
    String time;

    @Override
    public String toString() {
        return "TipsInfo{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", wherewant='" + wherewant + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipsInfo tipsInfo = (TipsInfo) o;

        if (id != tipsInfo.id) return false;
        if (!author.equals(tipsInfo.author)) return false;
        if (!wherewant.equals(tipsInfo.wherewant)) return false;
        return time.equals(tipsInfo.time);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + author.hashCode();
        result = 31 * result + wherewant.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWherewant() {
        return wherewant;
    }

    public void setWherewant(String wherewant) {
        this.wherewant = wherewant;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TipsInfo() {
    }

    public TipsInfo(int id, String author, String wherewant, String time) {
        this.id = id;
        this.author = author;
        this.wherewant = wherewant;
        this.time = time;
    }
}
