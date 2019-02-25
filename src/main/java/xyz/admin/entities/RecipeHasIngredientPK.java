/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Daniel GV
 */
@Embeddable
public class RecipeHasIngredientPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "recipe")
    private int recipe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ingredient")
    private int ingredient;

    public RecipeHasIngredientPK() {
    }

    public RecipeHasIngredientPK(int recipe, int ingredient) {
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public int getIngredient() {
        return ingredient;
    }

    public void setIngredient(int ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipe;
        hash += (int) ingredient;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeHasIngredientPK)) {
            return false;
        }
        RecipeHasIngredientPK other = (RecipeHasIngredientPK) object;
        if (this.recipe != other.recipe) {
            return false;
        }
        if (this.ingredient != other.ingredient) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xyz.admin.entities.RecipeHasIngredientPK[ recipe=" + recipe + ", ingredient=" + ingredient + " ]";
    }
    
}
