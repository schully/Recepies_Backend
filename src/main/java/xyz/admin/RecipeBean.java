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
import xyz.admin.entities.Category;
import xyz.admin.entities.Comment;
import xyz.admin.entities.Recipe;
import xyz.admin.entities.User;
import xyz.admin.sessionbeans.CategoryFacade;
import xyz.admin.sessionbeans.CommentFacade;
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

    private Recipe recipeForUpdateDialog;
    private Recipe recipeForCommentDialog;

    private int categoryId;

    private String picture;

    /**
     * Opens the update dialog with a given recipe.
     * @param recipe id of recipe in need of an update
     */
    public void openUpdateDialog(Recipe recipe) {
        this.recipeForUpdateDialog = recipe;
        User ufid = recipe.getUser();
        this.userId = ufid.getId();
        Category ctgr = recipe.getCategory();
        this.categoryId = ctgr.getId();
    }
    
    /**
     * Opens the comment dialog with a given recipe.
     * @param recipe The given recipe.
     */
    public void openCommentsDialog(Recipe recipe) {
        this.recipeForCommentDialog = recipe;
        User ufid = recipe.getUser();
        this.userId = ufid.getId();
        Category ctgr = recipe.getCategory();
        this.categoryId = ctgr.getId();
    }

    public void saveUpdateDialog() {
        /**
         * Get user_id from 'this' Get user with user_id from userFacade SetUpdateRecipe.user
         */
        User ufid = userFacade.find(this.userId);
        Category ctgr = categoryFacade.find(this.categoryId);
        recipeForUpdateDialog.setUser(ufid);
        recipeForUpdateDialog.setCategory(ctgr);
        recipeFacade.edit(recipeForUpdateDialog);
    }
    
    /**
     * Saves changes made in the comment dialog.
     */
    public void saveComments() {
        Recipe recipe = recipeForCommentDialog;
        
        for (Comment c : recipe.getCommentCollection()) {
            commentFacade.edit(c);
        }
    }

    public void deleteRecipe() {
        recipeFacade.remove(recipeForUpdateDialog);
    }

    //Auto generaed getters & setters
    public Recipe getRecipeForUpdateDialog() {
        return recipeForUpdateDialog;
    }

    public void setRecipeForUpdateDialog(Recipe updateRecipe) {
        this.recipeForUpdateDialog = updateRecipe;
    }

    public Recipe getRecipeForCommentDialog() {
        return recipeForCommentDialog;
    }

    public void setRecipeForCommentDialog(Recipe recipeForCommentDialog) {
        this.recipeForCommentDialog = recipeForCommentDialog;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @EJB
    RecipeFacade recipeFacade;
    @EJB
    UserFacade userFacade;
    @EJB
    CategoryFacade categoryFacade;
    @EJB
    CommentFacade commentFacade;

    /**
     *
     * @return recipes from database, sorted by id by default
     */
    public List<Recipe> getRecipes() {
        return recipeFacade.findAll();
    }

    public RecipeBean() {
        recipeForUpdateDialog = new Recipe(0);
    }

}
