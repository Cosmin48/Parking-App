package com.example.demo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdministratorController {
    @FXML
    private TableView<AdminControllerTableView> table_users;
    @FXML
    private TableColumn<AdminControllerTableView,String> column_username;
    @FXML
    private TableColumn<AdminControllerTableView, ImageView> column_image;
    @FXML
    private TableColumn<AdminControllerTableView, Button> accept;
    @FXML
    private TableColumn<AdminControllerTableView,Button> deny;

    public void admninUser(){
        try{
            DatabaseConnection connectNow=new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            ObservableList<AdminControllerTableView> list= FXCollections.observableArrayList();
            PreparedStatement ps=connectDB.prepareStatement("SELECT * from cityhall_account WHERE approve=0");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new AdminControllerTableView(rs.getString("Username")))
            }
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
}
