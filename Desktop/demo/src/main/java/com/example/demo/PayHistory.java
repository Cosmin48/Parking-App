package com.example.demo;

public class PayHistory {
    String carNumber,city,area,date;
    int time;
    public PayHistory(String carNumber,String city,String area,int time,String date){
        this.carNumber=carNumber;
        this.city=city;
        this.area=area;
        this.time=time;
        this.date=date;
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

    public int getTime() {
        return time;
    }

    public String getDate() {
        return date;
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

    public void setTime(int time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
