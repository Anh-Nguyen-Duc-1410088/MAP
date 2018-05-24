package com.ck5000.thuan.nongdanbiet.model;

import java.util.List;



public class OpenWeatherJSondaily {
    private City city;
    private int cnt;
    private List<DailyItem> list;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<DailyItem> getList() {
        return list;
    }

    public void setList(List<DailyItem> list) {
        this.list = list;
    }
}
