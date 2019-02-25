package com.example.easytour.createluggage;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by 黄家慧 on 2018/12/15.
 */

public class TimeBean implements IPickerViewData {
    private String  year;//年
    private List<TimemmBean> month;    //月

    //显示PickerView上的字符串
    //PickerView会通过IPickerViewData获取getPickerViewText方法显示出来
    @Override
    public String getPickerViewText() {
        return this.year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<TimemmBean> getMonth() {
        return month;
    }

    public void setMonth(List<TimemmBean> month) {
        this.month = month;
    }

    public static class TimemmBean {
        private String month;    //月
        private List<String> day;  //日

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public List<String> getDay() {
            return day;
        }

        public void setDay(List<String> day) {
            this.day = day;
        }
    }

}
