package com.example.demo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;
import javax.swing.*;

public class RegisterCityHallController {
    FileChooser fileChooser=new FileChooser();
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
    private TextField ibanTextField;

    @FXML
    private Label imageField;
    @FXML
    private ImageView imageView;
    @FXML
    private Label imageLabelError;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private InputStream fis;
    private static File file;
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
    public void findInCard_database(String card_number,ActionEvent event){
        String querry="SELECT count(1) from bank_account WHERE bank_account='"+card_number+"'";
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(querry);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    registerUser();
                    confirmPasswordLabel.setText("");
                    switchToCityHallStart(event);
                } else {
                    registrationMessageLabel.setText("Card number not found");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void registerButtonOnAction(ActionEvent event) throws IOException{
        if (setPasswordField.getText().equals(confirmPasswordField.getText())){
            findInCard_database(ibanTextField.getText(),event);
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
        try{
            DatabaseConnection connectNow=new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            String firstname=firstnameTextField.getText();
            String lastname=lastnameTextField.getText();
            String username=usernameTextField.getText();
            String password=setPasswordField.getText();
            String iban=ibanTextField.getText();
            PreparedStatement ps=connectDB.prepareStatement("INSERT INTO cityhall_account (firstname, lastname, username, password, iban, approve, image) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1,firstname);
            ps.setString(2,lastname);
            ps.setString(3,username);
            ps.setString(4,getEncryptedpassword(password));
            ps.setString(5,iban);
            ps.setInt(6,0);
            ps.setString(7,file.toURI().toString());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
    public void switchToCityHallStart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("cityhallStart.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void attachImage(ActionEvent event) throws IOException {
        fileChooser.setTitle("FileChooser");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
        file=fileChooser.showOpenDialog(null);
        if(file!=null) {
            imageView.setImage(new Image(file.toURI().toString()));
        } else imageLabelError.setText("Invalid image");
    }
}