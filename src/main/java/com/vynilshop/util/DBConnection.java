/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vynilshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static Connection connection;
    
    private DBConnection(){}
    
    
    public static Connection getDBConnection() throws SQLException, ClassNotFoundException{
        if(connection==null || connection.isClosed()){
            Class.forName(Constants.DRIVER_NAME);
            connection = DriverManager.getConnection(Constants.URL , Constants.USERNAME , Constants.PASSWORD);
        }
        return connection;
    }
}
