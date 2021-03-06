/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import xyz.admin.entities.Ingredient;

/**
 *
 * @author Daniel GV
 */
@Stateless
public class IngredientFacade extends AbstractFacade<Ingredient> {

    @PersistenceContext(unitName = "xyz.admin_Recepies_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngredientFacade() {
        super(Ingredient.class);
    }
    
}
