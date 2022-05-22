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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MainController implements Initializable {
 private Parent root;
 private Stage stage;
 private Scene scene;
 @FXML
 private Button closeButton;
    @FXML
    private TableView<PayHistory> tableView;
    @FXML
    private TableColumn<PayHistory,String> carNumber;
    @FXML
    private TableColumn<PayHistory,String> city;
    @FXML
    private TableColumn<PayHistory,String> area;
    @FXML
    private TableColumn<PayHistory,Integer> time;
    @FXML
    private TableColumn<PayHistory,String> date;
    private ObservableList<PayHistory> list= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try{
            DatabaseConnection connectNow=new DatabaseConnection();
            Connection connectDB=connectNow.getConnection();
            String usernameLogin=LoginController.username;
            PreparedStatement ps=connectDB.prepareStatement("SELECT * FROM paymenthistoryview WHERE username='"+usernameLogin+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new PayHistory(rs.getString("car_registration"),rs.getString("city"),rs.getString("area"),rs.getInt("time"),rs.getString("datapay")));
                System.out.println(list.get(0));
            }
            carNumber.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
            city.setCellValueFactory(new PropertyValueFactory<>("city"));
            area.setCellValueFactory(new PropertyValueFactory<>("area"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tableView.setItems(list);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void switchToPayment(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("paymentMain.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void closeButtonOnAction(ActionEvent event){
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    public void switchToViewHistory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("viewHistory.fxml"));
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
