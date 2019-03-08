/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.api;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import xyz.admin.utils.ConnectionFactory;

/**
 * Facade for interacting with the database; persisting recipes etc.
 *
 * @author Daniel GV
 */
public class RequestFacade {

    /**
     * @return The ID of the inserted recipe.
     */
    public int insertRecipe(String name, String description, String instructions) {
        try (Connection c = ConnectionFactory.getConnection()) {
            String sql = "INSERT into recipe (id, name, description, instructions, user) VALUES (NULL,?,?,?, 3)";
            PreparedStatement stmt = (PreparedStatement) c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, instructions);

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (int) generatedKeys.getLong(1);
                } else {
                    throw new RuntimeException("[insertRecipe] No generated keys");
                }
            }
        } catch (Exception e) {
            System.out.println("RequestFacade Error: " + e.getMessage());
            return 0;
        }

    }
}
