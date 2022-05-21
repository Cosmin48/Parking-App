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
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;


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

    public void registerButtonOnAction(ActionEvent event) throws IOException{
        if (setPasswordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            confirmPasswordLabel.setText("");
            switchToSuccess(event);

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


       String insertFields= "INSERT INTO user_account (firstname, lastname, username, password, card_number, budget) VALUES('";
       String insertValues=firstname+ "','" +lastname+ "','"+username+ "','"+ password+"','" +card_number+ "',"+(10+(new Random().nextInt(5)))+")";
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

}
