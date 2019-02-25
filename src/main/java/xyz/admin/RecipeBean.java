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

    private Recipe updateRecipe;

    private void setUpdaterecipeForm(int id) {
        this.updateRecipe = recipeFacade.find(id);
    }

    public Recipe getUpdateRecipe() {
        return updateRecipe;
    }

    public void setUpdateRecipe(Recipe updateRecipe) {
        this.updateRecipe = updateRecipe;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public List<Recipe> getRecipes() {
        return recipeFacade.findAll();
    }
    
    public String saveRecipe() {
        Recipe recipe = new Recipe();
        User user = userFacade.find(userId);
        recipe.setUser(user);
        recipeFacade.create(recipe);
        name = "";
        return "index";
    }

}
