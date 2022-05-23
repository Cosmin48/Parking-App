package com.example.demo;

public class ViewHistoryCityHallControllerUser {
    String carRegistration,area;
    int time;
    public ViewHistoryCityHallControllerUser(String carRegistration,String area,int time){
        this.carRegistration=carRegistration;
        this.area=area;
        this.time=time;
    }

    public String getCarRegistration() {
        return carRegistration;
    }

    public String getArea() {
        return area;
    }

    public int getTime() {
        return time;
    }

    public void setCarRegistration(String carRegistration) {
        this.carRegistration = carRegistration;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
