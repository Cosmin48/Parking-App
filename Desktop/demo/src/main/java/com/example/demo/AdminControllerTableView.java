package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class AdminControllerTableView {
    String firstname,lastname,username;

    public AdminControllerTableView(String firstname,String lastname,String username){
        this.firstname=firstname;
        this.lastname=lastname;
        this.username=username;
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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
