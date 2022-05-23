package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SeeRatingAdminController {
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private TableView<SeeRatingAdminUser> tableView;
    @FXML
    private TableColumn<SeeRatingAdminUser,String> area;
    @FXML
    private TableColumn<SeeRatingAdminUser,Integer> rating;


}
