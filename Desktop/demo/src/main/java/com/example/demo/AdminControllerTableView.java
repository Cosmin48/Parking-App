package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.sql.Blob;

public class AdminControllerTableView {
    String firstname,lastname,username,image;

    public AdminControllerTableView(String firstname,String lastname,String username,String image){
        this.firstname=firstname;
        this.lastname=lastname;
        this.username=username;
        this.image=image;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getImage() {
        return image;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
