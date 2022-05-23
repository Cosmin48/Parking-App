package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RateCityAdminController {
    private Parent root;
    private Scene scene;
    private Stage stage;
    public void switchToAdministrator(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("administrator.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void okButton(){

    }
    public void switchToSuccess(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("rateCityAdminSuccess.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
