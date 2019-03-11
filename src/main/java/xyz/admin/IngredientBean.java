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
import xyz.admin.entities.Ingredient;
import xyz.admin.sessionbeans.IngredientFacade;

/**
 *
 * @author Daniel GV
 */

@Named(value = "Ingredient")
@SessionScoped
public class IngredientBean implements Serializable{

    @EJB
    IngredientFacade ingredientFacade;

    public List<Ingredient> getIngredients() {
        return ingredientFacade.findAll();
    }
}
