/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel GV
 */
@Entity
@Table(name = "recipe_has_ingredient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecipeHasIngredient.findAll", query = "SELECT r FROM RecipeHasIngredient r")
    , @NamedQuery(name = "RecipeHasIngredient.findByRecipe", query = "SELECT r FROM RecipeHasIngredient r WHERE r.recipeHasIngredientPK.recipe = :recipe")
    , @NamedQuery(name = "RecipeHasIngredient.findByIngredient", query = "SELECT r FROM RecipeHasIngredient r WHERE r.recipeHasIngredientPK.ingredient = :ingredient")
    , @NamedQuery(name = "RecipeHasIngredient.findByQuantity", query = "SELECT r FROM RecipeHasIngredient r WHERE r.quantity = :quantity")})
public class RecipeHasIngredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipeHasIngredientPK recipeHasIngredientPK;
    @Size(max = 16)
    @Column(name = "quantity")
    private String quantity;
    @JoinColumn(name = "recipe", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe1;
    @JoinColumn(name = "ingredient", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ingredient ingredient1;

    public RecipeHasIngredient() {
    }

    public RecipeHasIngredient(RecipeHasIngredientPK recipeHasIngredientPK) {
        this.recipeHasIngredientPK = recipeHasIngredientPK;
    }

    public RecipeHasIngredient(int recipe, int ingredient) {
        this.recipeHasIngredientPK = new RecipeHasIngredientPK(recipe, ingredient);
    }

    public RecipeHasIngredientPK getRecipeHasIngredientPK() {
        return recipeHasIngredientPK;
    }

    public void setRecipeHasIngredientPK(RecipeHasIngredientPK recipeHasIngredientPK) {
        this.recipeHasIngredientPK = recipeHasIngredientPK;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe1() {
        return recipe1;
    }

    public void setRecipe1(Recipe recipe1) {
        this.recipe1 = recipe1;
    }

    public Ingredient getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(Ingredient ingredient1) {
        this.ingredient1 = ingredient1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeHasIngredientPK != null ? recipeHasIngredientPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeHasIngredient)) {
            return false;
        }
        RecipeHasIngredient other = (RecipeHasIngredient) object;
        if ((this.recipeHasIngredientPK == null && other.recipeHasIngredientPK != null) || (this.recipeHasIngredientPK != null && !this.recipeHasIngredientPK.equals(other.recipeHasIngredientPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xyz.admin.entities.RecipeHasIngredient[ recipeHasIngredientPK=" + recipeHasIngredientPK + " ]";
    }
    
}
