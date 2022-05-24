package com.example.demo;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
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
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField areaTextField;
    @FXML
    private TextField ratingTextField;
    @FXML
    private Label errorLabel;


     public void okButton(ActionEvent event) throws SQLException, IOException {
         DatabaseConnection connectNow=new DatabaseConnection();
         Connection connectDB= connectNow.getConnection();
         try {
             int value=Integer.parseInt(ratingTextField.getText());
         }catch(Exception e){
             errorLabel.setText("Not a number");
             return;
         }
         String query="SELECT count(1) FROM cityhall_account WHERE username='"+cityTextField.getText()+"' AND approve=1";
         PreparedStatement preparedStatement=connectDB.prepareStatement(query);
         ResultSet rs=preparedStatement.executeQuery();
         rs.next();
         if(rs.getInt(1)!=1) {
             errorLabel.setText("Invalid city");
             return;
         }
         String query1="SELECT count(1) FROM "+cityTextField.getText()+" WHERE area='"+areaTextField.getText()+"'";
         PreparedStatement preparedStatement1=connectDB.prepareStatement(query1);
         ResultSet rs1=preparedStatement1.executeQuery();
         rs1.next();
         if(rs1.getInt(1)!=1) {
             errorLabel.setText("Invalid area");
             return;
         }
         String querry= "INSERT INTO "+cityTextField.getText()+"_"+areaTextField.getText()+" (rating) VALUES  (?)";
         PreparedStatement ps= connectDB.prepareStatement(querry);
         ps.setInt(1,Integer.parseInt(ratingTextField.getText()));
         ps.executeUpdate();
         switchToOkRegister(event);


     }

    public void switchToOkRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("okRegister.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
