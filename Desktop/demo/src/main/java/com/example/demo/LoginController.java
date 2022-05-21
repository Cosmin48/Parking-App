package com.example.demo;

import javafx.application.Platform;
import javafx.collections.ObservableList;
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

import java.io.File;
import java.io.IOException;
import java.sql.*;
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
    private Stage stage;
    private Parent root;
    private Scene scene;
    private static int budget=0;

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
        String verifyLogin = "SElECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND password ='" + enterPasswordField.getText() + "' AND card_number ='" + enterCardNumberField.getText() +"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                   switchToMain(event);
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again!");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Button okButton;
    @FXML
    private Button closeButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField areaTextField;
    @FXML
    private TextField timeTextField;
    private ResultSet rs;
    private ObservableList<Integer> list;
    public void closeButtonOnAction(ActionEvent event){
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    private int price,new_budget;
    public void okButtonOnAction(ActionEvent event) throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String querry="SELECT price FROM "+cityTextField.getText()+"( WHERE area="+areaTextField.getText();
        PreparedStatement ps1=connectDB.prepareStatement(querry);
        rs= ps1.executeQuery();
        if(rs.next()) {
            price= rs.getInt("price");
        }
        else errorLabel.setText("Error");
        ps1.executeUpdate();
    }

}