package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RateCityAdminController {
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField ratingTextField;
    @FXML
    private Label errorLabel;
    public void switchToAdministrator(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("administrator.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void okButton(ActionEvent event) throws SQLException, IOException {
      DatabaseConnection connectNow=new DatabaseConnection();
      Connection connectDB= connectNow.getConnection();
      PreparedStatement ps=connectDB.prepareStatement("SELECT count(1) FROM cityhall_account WHERE username=? AND approve=1");
      ps.setString(1,cityTextField.getText());
      ResultSet rs=ps.executeQuery();
      while(rs.next()){
          if(rs.getInt(1)==1) {
              PreparedStatement ps1=connectDB.prepareStatement("UPDATE cityhall_account SET rating=? WHERE username=? AND approve=1");
              ps1.setInt(1,Integer.parseInt(ratingTextField.getText()));
              ps1.setString(2, cityTextField.getText());
              ps1.executeUpdate();
              switchToSuccess(event);
          }
          else errorLabel.setText("City not found");
      }
    }
    public void switchToSuccess(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("rateCityAdminSuccess.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
