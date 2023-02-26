/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vynilshop.service;

import com.vynilshop.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public boolean login(String username, String password) {
        boolean result = false;
        try {
            String usernamedb, passworddb;
            connection = DBConnection.getDBConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("select username , password from admin");
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                usernamedb = resultSet.getString("username");
                passworddb = resultSet.getString("password");

                if (usernamedb.equals(username) && passworddb.equals(password)) {
                    result = true;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            result = false;

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
