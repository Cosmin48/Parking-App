package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

public class CityhallRatingController {
    @FXML
    private Button okButton;
    @FXML
    private TextField areaTextField;
    @FXML
    private TextField label;
    @FXML
    private TextField trying;
    private Parent root;
    private Scene scene;
    private Stage stage;
    private String username=LoginCityHallController.username;
    public void okButton(ActionEvent event) throws SQLException, IOException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String querry="SELECT rating FROM "+username+" WHERE area='"+areaTextField.getText()+"'";
        PreparedStatement ps=connectDB.prepareStatement(querry);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            trying.setText(String.valueOf(rs.getInt("rating")));
        }

        ///switchToRatingView(event);
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
