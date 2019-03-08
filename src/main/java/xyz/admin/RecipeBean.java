/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import xyz.admin.entities.Recipe;
import xyz.admin.entities.User;
import xyz.admin.sessionbeans.RecipeFacade;
import xyz.admin.sessionbeans.UserFacade;

/**
 *
 * @author Daniel
 */
@Named(value = "Recipe")
@SessionScoped
public class RecipeBean implements Serializable {

    private int userId;

    private String name;

    private String description;
    
    private String instructions;
    
    private Recipe updateRecipe;

    /**
     * 
     * @param recipeId id of recipe in need of an update
     */
    public void openUpdateForm(int recipeId) {
        this.updateRecipe = recipeFacade.find(recipeId);
        User ufid = updateRecipe.getUser();
        this.userId = ufid.getId();
    }
    
    public void saveUpdatedRecipe() {
        /**
         * Get user_id from 'this'
         * Get user with user_id from userFacade
         * SetUpdateRecipe.user
         */
        User ufid = userFacade.find(this.userId);
        updateRecipe.setUser(ufid);
        recipeFacade.edit(updateRecipe);
    }
    
    public void deleteRecipe() {
        recipeFacade.remove(updateRecipe);
    }
    
    //Auto generaed getters & setters
    public Recipe getUpdateRecipe() {
        return updateRecipe;
    }

    public void setUpdateRecipe(Recipe updateRecipe) {
        this.updateRecipe = updateRecipe;
    }

    public int getUserId() {
        System.out.println("getUserId again " + userId);
        return userId;
    }

    public void setUserId(int userId) {
        System.out.println("Bamboolzed again " + userId);
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @EJB
    RecipeFacade recipeFacade;
    @EJB
    UserFacade userFacade;

    /**
     * 
     * @return recipes from database, sorted by id by default
     */
    public List<Recipe> getRecipes() {
        return recipeFacade.findAll();
    }
    
    /**
     * 
     * @return always "index", with the purpose of reloading the page 
     */
    public String saveRecipe() {
        Recipe recipe = new Recipe(null, name, description, instructions);
        User user = userFacade.find(userId);
        recipe.setUser(user);
        recipeFacade.create(recipe);
        name = "";
        return "index";
    }

    public RecipeBean() {
        updateRecipe = new Recipe(0);
    }

}
