package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import java.io.File;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController {
    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;
    public void loginButtonOnAction(ActionEvent event){
        loginMessageLabel.setText("You try to login");
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
}