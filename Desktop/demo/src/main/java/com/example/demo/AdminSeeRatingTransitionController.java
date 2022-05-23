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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AdminSeeRatingTransitionController {
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField cityTextField;
    @FXML
    private Label errorLabel;
    public static String cityName;

    public void okButton(ActionEvent event) throws IOException, SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB= connectNow.getConnection();
        String verifyCity="SELECT count(1) FROM cityhall_account WHERE username='"+cityTextField.getText()+"' AND approve=1";
        Statement ps=connectDB.createStatement();
        ResultSet rs=ps.executeQuery(verifyCity);
        while(rs.next()) {
            if(rs.getInt(1)==1) {
                cityName=cityTextField.getText();
                switchToSeeRating(event);
            }
            else errorLabel.setText("City not found");
        }
    }
    public void switchToSeeRating(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("seeRatingAdmin.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("administrator.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
