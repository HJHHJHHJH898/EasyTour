package com.example.easytour.createluggage;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by 黄家慧 on 2018/11/3.
 */

public class PlaceBean  implements IPickerViewData {

    private String  name;//省份
    private List<CityBean> city;    //市、区

    //显示PickerView上的字符串
    //PickerView会通过IPickerViewData获取getPickerViewText方法显示出来
    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        private String name;    //城市
        private List<String> area;  //区

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }

    }
}
