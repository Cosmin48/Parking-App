package com.example.demo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable{
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView imageView;
    @FXML
    private Button acceptButton;
    @FXML
    private Button denyButton;
    @FXML
    private TableView<AdminControllerTableView> table_users;
    @FXML
    private TableColumn<AdminControllerTableView,String> firstname;
    @FXML
    private TableColumn<AdminControllerTableView,String> lastname;
    @FXML
    private TableColumn<AdminControllerTableView,String> username;
    @FXML
    private Button cancelButton;
    @FXML
    private AnchorPane anchorPane;

    public void switchTocheckRequest(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("checkRequest.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private ResultSet rs;
    private ObservableList<AdminControllerTableView> list= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){
        try{
            DatabaseConnection connectNow=new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            PreparedStatement ps=connectDB.prepareStatement("SELECT firstname,lastname,username,image FROM cityhall_account WHERE approve=0");
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new AdminControllerTableView(rs.getString("firstname"),rs.getString("lastname"),rs.getString("username"),rs.getString("image")));
            }
            firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            table_users.setItems(list);
            table_users.setOnMouseClicked(e->{
                     AdminControllerTableView user= table_users.getSelectionModel().getSelectedItem();
                     Image image=new Image(user.getImage());
                     imageView.setImage(image);
                     acceptButton.setText("Accept");
                     acceptButton.setStyle("-fx-background-color: #0000ff; ");
                     denyButton.setText("Deny");
                     denyButton.setStyle("-fx-background-color: #0000ff; ");
                    }
                    );
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    private int index=0;
    public void acceptButtonOnAction(ActionEvent event) throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Random random=new Random();
        int buget=1000+random.nextInt(500);
        PreparedStatement ps = connectDB.prepareStatement("UPDATE cityhall_account SET approve=? WHERE username= ? ");
        ps.setInt(1,buget);
        ps.setString(2,list.get(index).getUsername());
        ps.executeUpdate();
        String querry="CREATE TABLE IF NOT EXISTS "+list.get(index).getUsername()+"(id int NOT NULL UNIQUE AUTO_INCREMENT, area VARCHAR(45) NOT NULL, price int NOT NULL, PRIMARY KEY(id))";
        PreparedStatement ps1=connectDB.prepareStatement(querry);
        ps1.executeUpdate();
        index++;
    }
    public void denyButtonOnAction(ActionEvent event) throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        PreparedStatement ps = connectDB.prepareStatement("UPDATE cityhall_account SET approve=-1 WHERE username= ? ");
        ps.setString(1,list.get(index).getUsername());
        ps.executeUpdate();
        index++;
    }
}