package com.example.demo;

public class PayHistory {
    String carNumber,city,area,time;
    public PayHistory(String carNumber,String city,String area,String time){
        this.carNumber=carNumber;
        this.city=city;
        this.area=area;
        this.time=time;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getTime() {
        return time;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
