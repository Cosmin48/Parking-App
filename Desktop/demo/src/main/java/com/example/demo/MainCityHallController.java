package com.example.demo;

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

import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class MainCityHallController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button closeButton;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField carRegistrationTextField;
    @FXML
    private Label errorLabel;
    public static String usernameSearch;
    public void switchToAddParkingArea(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addParkingArea.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private String username=LoginCityHallController.username;
    public void switchToFindUnpayed(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("findUnpayed.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String[] dropDate(String date){
        String digitsDate=date.replaceAll("[^0-9]","");
        String noDigitsDate=date.replaceAll("[0-9]","");
        String[] result=new String[4];
        result[0]="";
        result[1]="";
        result[2]="";
        result[3]=noDigitsDate;
        int count=digitsDate.length();
        for (int i=0;i<count;i++){
            String character=String.valueOf(digitsDate.charAt(count-i-1));
            if(i<4) {
                     result[3]=character+result[3];
            }
            else

            if(i<=5) {
                result[2]=character+result[2];
            }
            else
            if(i<=7) {
                if(!(i==7&&character.equals("0")))
                result[1]=character+result[1];
            }
            else
                if(i<=9) {
                    if(!(i==9&&character.equals("0")))
                    result[0]=character+result[0];
                }
                else {
                        result[3]=character+result[3];
                    }
        }
        return result;
    }
    public void verifyPayment(String date,int time, String area) {
        if (time == 0) {
            resultLabel.setText("Unpayed!");
            return;
        }
        String[] dateAsArray = dropDate(date);
        Date currentTime=new Date();
        String currentDate=String.valueOf(currentTime);
        String[] currentDateAsArray=dropDate(currentDate);
        if(! currentDateAsArray[3].equals(dateAsArray[3])){
            resultLabel.setText("Unpayed! No records for today.");
        }
        else if (Integer.parseInt(currentDateAsArray[0])-Integer.parseInt(dateAsArray[0])<time) {
            resultLabel.setText("Payed! Last record: "+date+" for "+time+" hour/s "+area+" area");
        }
        else if( (Integer.parseInt(currentDateAsArray[0])-Integer.parseInt(dateAsArray[0])==time) && (Integer.parseInt(currentDateAsArray[1])-Integer.parseInt(dateAsArray[1])<0)){
            resultLabel.setText("Payed! Last record: "+date+" for "+time+" hour/s "+area+" area");
        } else resultLabel.setText("Unpayed! Last record: "+date+" for "+time+" hour/s "+area+" area");
    }
    public void okButtonFindUnpayed(ActionEvent event) throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String date="";int time=0;String area="";
        PreparedStatement ps=connectDB.prepareStatement("SELECT * FROM paymenthistoryview WHERE car_registration=? AND city=?");
        ps.setString(1,carRegistrationTextField.getText());
        ps.setString(2,username);
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            date=rs.getString("datapay");
            time=rs.getInt("time");
            area=rs.getString("area");
        }
        verifyPayment(date,time,area);
    }
    public void okButton(ActionEvent event){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String query="SELECT count(1) FROM paymenthistoryview WHERE username='"+usernameTextField.getText()+"' AND city='"+username+"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while(queryResult.next()){
                if (queryResult.getInt(1)>=1) {
                    usernameSearch=usernameTextField.getText();
                    switchToviewHistory1(event);
                } else {
                    errorLabel.setText("Invalid username");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void closeButtonOnAction(ActionEvent event){
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    public void switchToSeeRating(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("accesRating.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToviewHistory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("viewHistoryCityHallTransition.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToviewHistory1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("viewHistoryCityHall.fxml"));
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
