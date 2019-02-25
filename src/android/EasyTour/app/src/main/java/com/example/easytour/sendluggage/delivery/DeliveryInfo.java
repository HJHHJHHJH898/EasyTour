package com.example.easytour.sendluggage.delivery;

/**
 * Created by 黄家慧 on 2018/12/14.
 */

public class DeliveryInfo {
    private String usrid;   //用户名
    private String now_city;    //所在城市
    private String now_address; //现在地址
    private String after_address;   //目的地址
    private int number;  //件数
    private String money;   //费用

    @Override
    public String toString() {
        return "DeliveryInfo{" +
                "usrid='" + usrid + '\'' +
                ", now_city='" + now_city + '\'' +
                ", now_address='" + now_address + '\'' +
                ", after_address='" + after_address + '\'' +
                ", number=" + number +
                ", money='" + money + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryInfo that = (DeliveryInfo) o;

        if (number != that.number) return false;
        if (!usrid.equals(that.usrid)) return false;
        if (!now_city.equals(that.now_city)) return false;
        if (!now_address.equals(that.now_address)) return false;
        if (!after_address.equals(that.after_address)) return false;
        return money.equals(that.money);

    }

    @Override
    public int hashCode() {
        int result = usrid.hashCode();
        result = 31 * result + now_city.hashCode();
        result = 31 * result + now_address.hashCode();
        result = 31 * result + after_address.hashCode();
        result = 31 * result + number;
        result = 31 * result + money.hashCode();
        return result;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public String getNow_city() {
        return now_city;
    }

    public void setNow_city(String now_city) {
        this.now_city = now_city;
    }

    public String getNow_address() {
        return now_address;
    }

    public void setNow_address(String now_address) {
        this.now_address = now_address;
    }

    public String getAfter_address() {
        return after_address;
    }

    public void setAfter_address(String after_address) {
        this.after_address = after_address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public DeliveryInfo(String usrid, String now_city, String now_address, String after_address, int number, String money) {
        this.usrid = usrid;
        this.now_city = now_city;
        this.now_address = now_address;
        this.after_address = after_address;
        this.number = number;
        this.money = money;
    }

    public DeliveryInfo() {
    }
}
