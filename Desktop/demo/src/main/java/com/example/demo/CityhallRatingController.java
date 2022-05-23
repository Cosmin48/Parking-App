package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CityhallRatingController{
    @FXML
    private Button okButton;
    @FXML
    private TextField areaTextField;
    @FXML
    private Label errorLabel;
    private Parent root;
    private Scene scene;
    private Stage stage;
    private String usernameLog=LoginCityHallController.username;
    public static String area;

    public void okButton(ActionEvent event){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String query="SELECT count(1) FROM "+usernameLog+" WHERE area='"+areaTextField.getText()+"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    area=areaTextField.getText();
                    switchToRatingView(event);
                } else {
                    errorLabel.setText("Invalid area");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void switchToRatingView(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("ratingview.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    public void switchToMainCityHall(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("maincityhall.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
