package com.example.demo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;
import java.net.URL;
public class LoginController{
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
    public static String username;
    private static String cardNumber;

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
        String verifyLogin = "SElECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND password ='" + getEncryptedpassword(enterPasswordField.getText()) + "' AND card_number ='" + enterCardNumberField.getText() +"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    username=usernameTextField.getText();
                    cardNumber=enterCardNumberField.getText();
                    String text="SELECT budget FROM card_numbers WHERE card_numbers='"+cardNumber+"'";
                    PreparedStatement preparedStatement=connectDB.prepareStatement(text);
                    ResultSet rs=preparedStatement.executeQuery();
                    while(rs.next()){
                    budget=rs.getInt("budget");
                    }
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
    @FXML
    private TextField registrationNumber;

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    private int price,budgetBank;
    private String iban;
    public String[] dropDate(String date){
        String digitsDate=date.replaceAll("[^0-9]","");
        String noDigitsDate=date.replaceAll("[0-9]","");
        String[] result=new String[4];
        result[0]="";
        result[1]="";
        result[2]="";
        result[3]=noDigitsDate;
        int count=digitsDate.length();
        for (int i=0;i<count;i++){
            String character=String.valueOf(digitsDate.charAt(count-i-1));
            if(i<4) {
                result[3]=character+result[3];
            }
            else

            if(i<=5) {
                result[2]=character+result[2];
            }
            else
            if(i<=7) {
                result[1]=character+result[1];
            }
            else
            if(i<=9) {
                result[0]=character+result[0];
            }
            else {
                result[3]=character+result[3];
            }
        }
        return result;
    }
    public void okButtonOnAction(ActionEvent event) throws SQLException, IOException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String querry="SELECT price FROM "+cityTextField.getText()+" WHERE area='"+areaTextField.getText()+"'";
        PreparedStatement ps1=connectDB.prepareStatement(querry);
        ResultSet rs = ps1.executeQuery();
        while(rs.next()) {
            price= rs.getInt("price");
        }
        int new_budget=budget-price*Integer.parseInt(timeTextField.getText());
        Date currentTime=new Date();
        String[] currentTimeDropped=dropDate(String.valueOf(currentTime));
        if(new_budget<=0) errorLabel.setText("Error. Not enough founds");
        else if(Integer.parseInt(timeTextField.getText())>4) errorLabel.setText("Maximum 4 hours");
         else if(Integer.parseInt(currentTimeDropped[0])+Integer.parseInt(timeTextField.getText())>23 || Integer.parseInt(currentTimeDropped[0])<8) errorLabel.setText("Outside of working hours");
                     else {
                         budget=new_budget;
                         String querry1="UPDATE card_numbers SET budget= "+budget+" WHERE card_numbers='"+cardNumber+"'";
                         PreparedStatement ps=connectDB.prepareStatement(querry1);
                         ps.executeUpdate();
                         String querry2="SELECT * FROM cityhall_account WHERE username='"+cityTextField.getText()+"'";
                         PreparedStatement ps2=connectDB.prepareStatement(querry2);
                         ResultSet resultSet=ps2.executeQuery();
                         while(resultSet.next()){
                             iban=resultSet.getString("iban");
                         }
                         String querry4="SELECT * FROM bank_account WHERE bank_account='"+iban+"'";
                         PreparedStatement ps4=connectDB.prepareStatement(querry4);
                         ResultSet resultSet1=ps4.executeQuery();
                         while(resultSet1.next()){
                         budgetBank=resultSet1.getInt("budget");
                         }
                         budgetBank=budgetBank+price*Integer.parseInt(timeTextField.getText());
                         String querry3="UPDATE bank_account SET budget= "+budgetBank+" WHERE bank_account='"+iban+"'";
                         PreparedStatement ps3=connectDB.prepareStatement(querry3);
                         ps3.executeUpdate();
                         String querry5="INSERT INTO paymenthistoryview (username,car_registration,city,area,time,datapay) VALUES (?, ?, ?, ?, ?, ?)";
                         PreparedStatement ps6=connectDB.prepareStatement(querry5);
                         ps6.setString(1,username);
                         ps6.setString(2,registrationNumber.getText());
                         ps6.setString(3,cityTextField.getText());
                         ps6.setString(4,areaTextField.getText());
                         ps6.setInt(5,Integer.parseInt(timeTextField.getText()));
                         ps6.setString(6, String.valueOf(currentTime));
                         ps6.executeUpdate();
                         switchToOkPayment(event);
        }
    }
    public void switchToOkPayment(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("okPaymentWindow.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}