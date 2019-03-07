/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Daniel GV
 */
public class ConnectionFactory {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String host = "localhost:6767";
        String username = "recipeboi";
        String password = "4444";
        String db = "Recipes";
        String url = String.format("jdbc:mysql://%s/%s", host, db);
        Class.forName("com.mysql.jdbc.Driver");
        return (Connection) DriverManager.getConnection(url, username, password);

    }
}
