package com.example.demo;

public class SeeRatingAdminUser {
    String area;
    int rating;
    public SeeRatingAdminUser(String area,int rating){
        this.area=area;
        this.rating=rating;
    }

    public String getArea() {
        return area;
    }

    public int getRating() {
        return rating;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
