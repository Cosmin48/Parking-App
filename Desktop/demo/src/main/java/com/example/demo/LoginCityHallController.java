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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    @FXML
    private Label errorLabel;
    private String getEncryptedpassword(String password) {
        String encryptedpassword = null;
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());

            byte[] bytes = m.digest();

            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encryptedpassword = s.toString();
        }
        catch (
                NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return encryptedpassword;
    }
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
        String verifyLogin = "SElECT count(1) FROM cityhall_account WHERE username = '" + usernameTextField.getText() + "' AND password = '" + getEncryptedpassword(enterPasswordField.getText()) + "' AND approve>0";
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
        try {
            int value=Integer.parseInt(priceField.getText());
        }catch(Exception e){
            errorLabel.setText("Not a number");
            return;
        }
        String querry="INSERT INTO "+username+ " (area,price) VALUES (?,?)";
        PreparedStatement ps=connectDB.prepareStatement(querry);
        ps.setString(1,nameField.getText());
        ps.setInt(2,Integer.parseInt(priceField.getText()));
        ps.executeUpdate();
        String query="CREATE TABLE IF NOT EXISTS "+username+"_"+nameField.getText()+"(id int NOT NULL UNIQUE AUTO_INCREMENT, rating int NOT NULL, PRIMARY KEY(id))";
        PreparedStatement ps1=connectDB.prepareStatement(query);
        ps1.executeUpdate();
    }
}