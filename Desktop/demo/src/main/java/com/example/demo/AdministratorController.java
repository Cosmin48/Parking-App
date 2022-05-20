package com.example.demo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable{
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<AdminControllerTableView> table_users;
    @FXML
    private TableColumn<AdminControllerTableView,String> firstname;
    @FXML
    private TableColumn<AdminControllerTableView,String> lastname;
    @FXML
    private TableColumn<AdminControllerTableView,String> username;

    public void switchTocheckRequest(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("checkRequest.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){
        try{
            DatabaseConnection connectNow=new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            ObservableList<AdminControllerTableView> list= FXCollections.observableArrayList();
            PreparedStatement ps=connectDB.prepareStatement("SELECT firstname,lastname,username FROM cityhall_account WHERE approve=0");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new AdminControllerTableView(rs.getString("firstname"),rs.getString("lastname"),rs.getString("username")));
            }
            firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            table_users.setItems(list);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
