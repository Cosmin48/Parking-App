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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RatingViewController implements Initializable {
    @FXML
    private Button okButton;
    @FXML
    private TextField areaTextField;
    @FXML
    private TableView<RatingUser> table;
    @FXML
    private TableColumn<RatingUser,Integer> id;
    @FXML
    private TableColumn<RatingUser,Integer> mark;
    private Parent root;
    private Scene scene;
    private Stage stage;
    private String username=LoginCityHallController.username;

    public void switchToMainCityHall(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("maincityhall.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private ResultSet rs;
    private String area=CityhallRatingController.area;
    private ObservableList<RatingUser> list= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try{
            DatabaseConnection connectNow=new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            PreparedStatement ps=connectDB.prepareStatement("SELECT rating FROM "+username+"_"+area);
            rs=ps.executeQuery();
            int index=1;
            while(rs.next()){
                list.add(new RatingUser(index,rs.getInt("rating")));
                index++;
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
            table.setItems(list);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
