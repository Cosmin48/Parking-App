package com.example.demo;

public class RatingUser {
    int id, mark;
    public RatingUser(int id,int mark){
        this.id=id;
        this.mark=mark;
    }

    public int getId() {
        return id;
    }

    public int getMark() {
        return mark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
