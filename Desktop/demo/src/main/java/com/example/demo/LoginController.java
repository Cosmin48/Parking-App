package com.example.demo;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class LoginController {
    private Button cancelButton;
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
}