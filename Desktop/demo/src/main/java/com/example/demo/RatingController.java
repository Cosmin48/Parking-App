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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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


     public void okButton(ActionEvent event) throws SQLException, IOException {
         DatabaseConnection connectNow=new DatabaseConnection();
         Connection connectDB= connectNow.getConnection();
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
