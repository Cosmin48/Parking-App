package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class AdminControllerTableView {
    String username;
    ImageView request;
    Button accept,deny;
    public AdminControllerTableView(String username,ImageView request,Button accept, Button deny){
        this.username=username;
        this.request=request;
        this.accept=accept;
        this.deny=deny;
    }

    public String getUsername() {
        return username;
    }

    public ImageView getRequest() {
        return request;
    }

    public Button getAccept() {
        return accept;
    }

    public Button getDeny() {
        return deny;
    }

    public void setRequest(ImageView request) {
        this.request = request;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccept(Button accept) {
        this.accept = accept;
    }

    public void setDeny(Button deny) {
        this.deny = deny;
    }
}
