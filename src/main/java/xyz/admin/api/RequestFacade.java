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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.admin.entities.Category;
import xyz.admin.entities.Comment;
import xyz.admin.entities.Recipe;
import xyz.admin.entities.User;
import xyz.admin.sessionbeans.CategoryFacade;
import xyz.admin.sessionbeans.RecipeFacade;
import xyz.admin.sessionbeans.UserFacade;
import xyz.admin.utils.ConnectionFactory;

/**
 * Facade for interacting with the database; persisting recipes etc.
 *
 * @author Daniel GV
 */
@Stateless
public class RequestFacade {

    @EJB
    RecipeFacade recipeFacade;

    @EJB
    UserFacade userFacade;

    @EJB
    CategoryFacade categoryFacade;
    
    public Recipe getRecipe(int id) {
        return recipeFacade.find(id);
    }

    /**
     * @return The ID of the inserted recipe.
     */
    public int insertRecipe(String authorUsername, String category, String name, String description, String instructions) {
        Category kittyCat = categoryFacade.findByName(category);
        if (kittyCat == null) {
            //  category does not exist
            kittyCat = new Category();
            kittyCat.setName(category);

            try {
                categoryFacade.create(kittyCat);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        User user = userFacade.findByUsername(authorUsername);
        
        if (user == null) {
            if (true) {
                throw new IllegalArgumentException("Unknown username: " + authorUsername);
            }
        }
        
        Recipe recipe = new Recipe(name, kittyCat, description, instructions, "");
        recipe.setUser(user);
        recipeFacade.create(recipe);
        
        recipeFacade.flush();

        return recipe.getId();
    }

    public void postComment(int userId, int recipeId, String text) {
        Comment comment = new Comment(null, text);
        Recipe recipe = recipeFacade.find(recipeId);

        if (recipe == null) {
            //  TODO: handle
            return;
        }

        comment.setRecipe(recipe);

        User user = userFacade.find(userId);
        if (user == null) {
            return;
        }

        comment.setUser(user);
    }

}
