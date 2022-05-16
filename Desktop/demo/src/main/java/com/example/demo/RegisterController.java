package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;


public class RegisterController {
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void registerButtonOnAction(ActionEvent event){
        registrationMessageLabel.setText("User has been registered succesfully!");
        registerUser();
    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
    }

    public void registerUser(){

        if (setPasswordField.getText().equals(confirmPasswordField.getText())){
            confirmPasswordLabel.setText("You are set");

        }else{
            confirmPasswordLabel.setText("Password does not match");

        }
    }
    public void switchToLogin(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("login.fxml"));
         stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         scene=new Scene(root);
         stage.setScene(scene);
         stage.show();
    }

}
