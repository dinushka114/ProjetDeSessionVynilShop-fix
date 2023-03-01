/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vynilshop.service;

import com.vynilshop.model.Order;
import com.vynilshop.model.User;
import com.vynilshop.util.DBConnection;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class CustomerService {

    private static Connection connection;
    private static PreparedStatement preparedStatement, preparedStatement_temp;
    private static ResultSet resultSet;

    public boolean registerCustomer(User user) {
        boolean result = false;

        try {

            connection = DBConnection.getDBConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO users (name , email , password) values(? , ? , ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
            connection.commit();
            result = true;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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

    public int getUserIdByEmail(String email) {
        int userId = 0;

        try {
            connection = DBConnection.getDBConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("select id from users where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userId = resultSet.getInt("id");
                System.out.println("Searching " + userId);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

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

        return userId;
    }

    public boolean loginCustomer(String username, String password) {
        boolean result = false;
        int userId = 0;
        try {
            String usernamedb, passworddb;

            connection = DBConnection.getDBConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("select email , password , id from users");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usernamedb = resultSet.getString("email");
                passworddb = resultSet.getString("password");

                if (usernamedb.equals(username) && passworddb.equals(password)) {
                    result = true;
                    userId = resultSet.getInt("id");
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

    public static String generateOrderId() {
        String generateUUIDNo = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        String unique_no = generateUUIDNo.substring(generateUUIDNo.length() - 10);
        System.out.println("unique " + unique_no);
        return unique_no;
    }

    public boolean makeOrder(ArrayList<Order> orders, int user_id) {
        boolean result = false;

        try {

            connection = DBConnection.getDBConnection();
            connection.setAutoCommit(false);

            String order_id = generateOrderId();

            for (Order order : orders) {
                preparedStatement = connection.prepareStatement("INSERT INTO orders ( order_id , product_id , user_id , quantity ) values(? , ? , ? , ?)");

                preparedStatement.setString(1, order_id);

                preparedStatement.setInt(2, order.getProductId());
                preparedStatement.setInt(3, order.getUid());
                preparedStatement.setInt(4, order.getQunatity());
                preparedStatement.execute();
                connection.commit();
            }

            preparedStatement_temp = connection.prepareStatement("INSERT INTO purchase_order (order_id , user_id , status) value(?,?,?)");
            preparedStatement_temp.setString(1, order_id);
            preparedStatement_temp.setInt(2, user_id);
            preparedStatement_temp.setString(3, "pending");
            preparedStatement_temp.execute();
            connection.commit();

            result = true;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
