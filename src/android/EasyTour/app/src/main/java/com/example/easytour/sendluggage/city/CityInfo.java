package com.example.easytour.sendluggage.city;

/**
 * Created by 黄家慧 on 2018/12/2.
 */

public class CityInfo {
    private String Zimu;
    private String Location1;
    private String Location2;
    private String Location3;
    private String Location4;
    private String Location5;

    public CityInfo(String zimu, String location1, String location2, String location3, String location4, String location5) {
        Zimu = zimu;
        Location1 = location1;
        Location2 = location2;
        Location3 = location3;
        Location4 = location4;
        Location5 = location5;
    }

    public CityInfo() {
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "Zimu='" + Zimu + '\'' +
                ", Location1='" + Location1 + '\'' +
                ", Location2='" + Location2 + '\'' +
                ", Location3='" + Location3 + '\'' +
                ", Location4='" + Location4 + '\'' +
                ", Location5='" + Location5 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityInfo cityInfo = (CityInfo) o;

        if (!Zimu.equals(cityInfo.Zimu)) return false;
        if (!Location1.equals(cityInfo.Location1)) return false;
        if (!Location2.equals(cityInfo.Location2)) return false;
        if (!Location3.equals(cityInfo.Location3)) return false;
        if (!Location4.equals(cityInfo.Location4)) return false;
        return Location5.equals(cityInfo.Location5);

    }

    @Override
    public int hashCode() {
        int result = Zimu.hashCode();
        result = 31 * result + Location1.hashCode();
        result = 31 * result + Location2.hashCode();
        result = 31 * result + Location3.hashCode();
        result = 31 * result + Location4.hashCode();
        result = 31 * result + Location5.hashCode();
        return result;
    }

    public String getZimu() {
        return Zimu;
    }

    public void setZimu(String zimu) {
        Zimu = zimu;
    }

    public String getLocation1() {
        return Location1;
    }

    public void setLocation1(String location1) {
        Location1 = location1;
    }

    public String getLocation2() {
        return Location2;
    }

    public void setLocation2(String location2) {
        Location2 = location2;
    }

    public String getLocation3() {
        return Location3;
    }

    public void setLocation3(String location3) {
        Location3 = location3;
    }

    public String getLocation4() {
        return Location4;
    }

    public void setLocation4(String location4) {
        Location4 = location4;
    }

    public String getLocation5() {
        return Location5;
    }

    public void setLocation5(String location5) {
        Location5 = location5;
    }
}
