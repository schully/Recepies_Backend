/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.utils.auth;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import xyz.admin.utils.ConnectionFactory;

/**
 *
 * @author Daniel GV
 */
public class CredentialFacade {

    public static Credentials createCredentials(String basicAuth) {
        return null;
    }

    public static void save(Credentials credentials) {

    }

    public static Credentials get(String username) throws SQLException, ClassNotFoundException {
        try (Connection c = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM user WHERE username ";
            PreparedStatement stmt = (PreparedStatement) c.prepareStatement(sql);
            

            stmt.executeUpdate();
        }
    }
}
