/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vynilshop.service;

import com.vynilshop.model.Product;
import static com.vynilshop.util.Constants.DEFAULT_BUFFER_SIZE;
import com.vynilshop.util.DBConnection;
import com.vynilshop.util.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

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

    public boolean addProduct(Product product) throws IOException {
        boolean res = false;
        try {

            connection = DBConnection.getDBConnection();
            connection.setAutoCommit(false);

            byte[] fileContent = FileUtil.getFileContent(product.getImage());
            String imgData = Base64.getEncoder().encodeToString(fileContent);

            preparedStatement = connection.prepareStatement("INSERT INTO products ( name, artist, price, image , genre, description, year ) values(? , ? , ? , ? , ? , ? , ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getArtist());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, imgData);
            preparedStatement.setString(5, product.getGenre());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getYear());
            preparedStatement.execute();
            connection.commit();
            res = true;

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

        return res;
    }

    public boolean updateProduct(Product product) {
        boolean result = false;
        try {

            connection = DBConnection.getDBConnection();
            byte[] fileContent = FileUtil.getFileContent(product.getImage());
            String imgData = Base64.getEncoder().encodeToString(fileContent);
            preparedStatement = connection.prepareStatement("UPDATE products set name = ? , artist = ? , price = ? , image = ? , genre = ? , description = ? , year = ? where id = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getArtist());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, imgData);
            preparedStatement.setString(5, product.getGenre());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getYear());
            preparedStatement.setInt(8, product.getId());
            result = preparedStatement.executeUpdate() > 0;

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

    public boolean deleteProduct(int id) {
        boolean result = false;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement("delete from products where id = ? ");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate() > 0;

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
    
    public boolean deleteCustomer(int id) {
        boolean result = false;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement("delete from users where id = ? ");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate() > 0;

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
