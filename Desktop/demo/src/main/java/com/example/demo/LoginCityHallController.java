package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginCityHallController {
    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    private Parent root;
    private Scene scene;
    private Stage stage;
    public static String username;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    public void loginButtonOnAction(ActionEvent event){
        if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            validateLogin(event);
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin(ActionEvent event){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String verifyLogin = "SElECT count(1) FROM cityhall_account WHERE username = '" + usernameTextField.getText() + "' AND password = '" + enterPasswordField.getText() + "' AND approve>0";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    username=usernameTextField.getText();
                    switchToMain(event);
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again!");
                    usernameTextField.setText("");
                    enterPasswordField.setText("");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("maincityhall.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addButton(ActionEvent event) throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String querry="INSERT INTO "+username+ " (area,price) VALUES (?,?)";
        PreparedStatement ps=connectDB.prepareStatement(querry);
        ps.setString(1,nameField.getText());
        ps.setInt(2,Integer.parseInt(priceField.getText()));
        ps.executeUpdate();
    }
}