/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import xyz.admin.utils.ConnectionFactory;

/**
 *
 * @author Daniel GV
 */
public class CredentialFacade {

    public static Credentials createCredentials(String basicAuth) {
        basicAuth = basicAuth.substring(6).trim();
        byte[] bytes = Base64.getDecoder().decode(basicAuth);
        basicAuth = new String(bytes);
        int colon = basicAuth.indexOf(":");
        String username = basicAuth.substring(0, colon);
        String password = basicAuth.substring(colon + 1);
        return new Credentials(username, password);
    }

    public static boolean save(Credentials credentials) throws SQLException, ClassNotFoundException {
        String hashed = BCrypt.withDefaults().hashToString(12, credentials.getPassword().toCharArray());
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            String sql = String.format("INSERT INTO user (id,username,password) VALUES(NULL,'%s','%s')", credentials.getUsername(), hashed);
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("saveCredenetialsError: " + e.getMessage());
            return false;
        }
    }

    public static Credentials get(String username) throws SQLException, ClassNotFoundException {
        try (Connection c = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet data = stmt.executeQuery();
            if (data.next()) {
                return new Credentials(data.getString("username"), data.getString("password"));
            } else {
                System.out.println("No user found for username: " + username);
                return null;
            }
        } catch (Exception e) {
            System.out.println("getCredentialsError: " + e.getMessage());
            return null;
        }
    }

    public static boolean verify(String username, String password) throws SQLException, ClassNotFoundException {
        Credentials credentials = get(username);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), credentials.getPassword());
        return result.verified;
    }
}
