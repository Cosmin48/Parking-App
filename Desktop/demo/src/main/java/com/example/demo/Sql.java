package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
    public static void main(String args[])
    {
        String url="jdbc:mysql://localhost:3306/parking";
        String user="root";
        String password="12345678";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection(url,user,password);
            System.out.println("Connection is Succesful to the data base"+url);
            String update="Insert into parking_users(Id,Nume) values(101,'ion')";


            Statement statament=connection.createStatement();
            statament.executeUpdate(update);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        }
    }

