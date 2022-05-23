package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class RegisterControllerTest extends RegisterController {

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
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField cardnumberTextField;

    private Parent root;
    private Stage stage;
    private Scene scene;

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
    public void findInCard_database(String card_number, ActionEvent event){
        String querry="SELECT count(1) from card_numbers WHERE card_numbers='"+card_number+"'";
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(querry);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    registerUser();
                    confirmPasswordLabel.setText("");
                    switchToSuccess(event);
                } else {
                    registrationMessageLabel.setText("Card number not found");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void registerButtonOnAction(ActionEvent event) throws IOException {
        if (setPasswordField.getText().equals(confirmPasswordField.getText())){
            findInCard_database(cardnumberTextField.getText(),event);

        }else{
            registrationMessageLabel.setText("");
            confirmPasswordLabel.setText("Password does not match");

        }

    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();

        String firstname=firstnameTextField.getText();
        String lastname=lastnameTextField.getText();
        String username=usernameTextField.getText();
        String password=setPasswordField.getText();
        String card_number=cardnumberTextField.getText();


        String insertFields= "INSERT INTO user_account (firstname, lastname, username, password, card_number) VALUES('";
        String insertValues=firstname+ "','" +lastname+ "','"+username+ "','"+ getEncryptedpassword(password) +"','" +card_number+ "')";
        String insertToRegister=insertFields+ insertValues;

        try{
            Statement statement=connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessageLabel.setText("User has been registered successfully!");
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
    public void switchToSuccess(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("success.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void findInCard_database() {
    }

    @Test
    void registerButtonOnAction() {
    }

    @Test
    void closeButtonOnAction() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void switchToSuccess() {
    }

    @Test
    void switchToLogin() {
    }
}