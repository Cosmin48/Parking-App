package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SeeRatingAdminController implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private TableView<SeeRatingAdminUser> tableView;
    @FXML
    private TableColumn<SeeRatingAdminUser,String> area;
    @FXML
    private TableColumn<SeeRatingAdminUser,Integer> rating;
    private String username=AdminSeeRatingTransitionController.cityName;
    private ResultSet rs;
    private ObservableList<SeeRatingAdminUser> list= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try{
            DatabaseConnection connectNow=new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            PreparedStatement ps=connectDB.prepareStatement("SELECT area FROM "+username);
            rs=ps.executeQuery();
            while(rs.next()){
                String area=rs.getString("area");
                PreparedStatement ps1=connectDB.prepareStatement("SELECT rating FROM "+username+"_"+area);
                ResultSet rs1=ps1.executeQuery();
                while(rs1.next()) {
                    list.add(new SeeRatingAdminUser(area, rs1.getInt("rating")));
                }
            }
            area.setCellValueFactory(new PropertyValueFactory<>("area"));
            rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
            tableView.setItems(list);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
