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
    }

    @Test
    void getUsername() {
    }

    @Test
    void getImage() {
    }

    @Test
    void setFirstname() {
    }

    @Test
    void setLastname() {
    }

    @Test
    void setUsername() {
    }

    @Test
    void setImage() {
    }
}