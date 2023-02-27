/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vynilshop.service;

import com.vynilshop.model.User;
import com.vynilshop.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {
    
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    
    
    public boolean registerCustomer(User user){
        boolean result = false;
        
        try {
				
			connection = DBConnection.getDBConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO users (name , email , password) values(? , ? , ?)");
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getEmail());
			preparedStatement.setString(3,user.getPassword());
			preparedStatement.execute();
			connection.commit();
			result = true;
			
			
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
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
