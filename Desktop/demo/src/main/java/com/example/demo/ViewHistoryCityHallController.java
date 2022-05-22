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

public class ViewHistoryCityHallController implements Initializable {
    @FXML
    private TableView<ViewHistoryCityHallControllerUser> table;
    @FXML
    private TableColumn<ViewHistoryCityHallControllerUser,String> carRegistration;
    @FXML
    private TableColumn<ViewHistoryCityHallControllerUser,String> area;
    @FXML
    private TableColumn<ViewHistoryCityHallControllerUser,Integer> time;
    private Parent root;
    private Scene scene;
    private Stage stage;


    public void switchToMainCityHall(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("maincityhall.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private ResultSet rs;
    private String usernameText=MainCityHallController.usernameSearch;
    private String username=LoginCityHallController.username;
    private ObservableList<ViewHistoryCityHallControllerUser> list= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try{
            DatabaseConnection connectNow=new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            PreparedStatement ps=connectDB.prepareStatement("SELECT * FROM paymenthistoryview WHERE username='"+usernameText+"' AND city='"+username+"'");
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new ViewHistoryCityHallControllerUser(rs.getString("car_registration"),rs.getString("area"),rs.getInt("time")));
            }
            carRegistration.setCellValueFactory(new PropertyValueFactory<>("carRegistration"));
            area.setCellValueFactory(new PropertyValueFactory<>("area"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            table.setItems(list);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
