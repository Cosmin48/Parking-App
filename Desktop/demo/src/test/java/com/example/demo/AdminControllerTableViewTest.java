package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTableViewTest {

    @Test
    void getFirstname() {
        AdminControllerTableView adminControllerTableView=new AdminControllerTableView();
        adminControllerTableView.setFirstname("test");
        assertTrue(adminControllerTableView.getFirstname()=="test");
    }

    @Test
    void getLastname() {
        AdminControllerTableView adminControllerTableView=new AdminControllerTableView();
        adminControllerTableView.setLastname("test");
        assertTrue(adminControllerTableView.getLastname()=="test");
    }

    @Test
    void getUsername() {
        AdminControllerTableView adminControllerTableView=new AdminControllerTableView();
        adminControllerTableView.setUsername("test");
        assertTrue(adminControllerTableView.getUsername()=="test");

    }

    @Test
    void getImage() {
        AdminControllerTableView adminControllerTableView=new AdminControllerTableView();
        adminControllerTableView.setImage("test");
        assertTrue(adminControllerTableView.getImage()=="test");

    }

    @Test
    void setFirstname() {
        AdminControllerTableView adminControllerTableView=new AdminControllerTableView();
        adminControllerTableView.setFirstname("test");
        assertTrue(adminControllerTableView.getFirstname()=="test");

    }

    @Test
    void setLastname() {
        AdminControllerTableView adminControllerTableView=new AdminControllerTableView();
        adminControllerTableView.setLastname("test");
        assertTrue(adminControllerTableView.getLastname()=="test");

    }

    @Test
    void setUsername() {
        AdminControllerTableView adminControllerTableView=new AdminControllerTableView();
        adminControllerTableView.setUsername("test");
        assertTrue(adminControllerTableView.getUsername()=="test");

    }

    @Test
    void setImage() {
        AdminControllerTableView adminControllerTableView=new AdminControllerTableView();
        adminControllerTableView.setImage("test");
        assertTrue(adminControllerTableView.getImage()=="test");

    }
}