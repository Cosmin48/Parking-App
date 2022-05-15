package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController {
    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private TextField enterCardNumberField;

    public void loginButtonOnAction(ActionEvent event){
        if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin(){
       DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String verifyLogin = "SElECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND password ='" + enterPasswordField.getText() + "' AND card_number ='" + enterCardNumberField.getText() +"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                   loginMessageLabel.setText("Welcome!");
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again!");
                    usernameTextField.setText("");
                    enterPasswordField.setText("");
                    enterCardNumberField.setText("");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}