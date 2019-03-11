/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.admin.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import xyz.admin.entities.Category;

/**
 *
 * @author Daniel GV
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "xyz.admin_Recepies_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Category findByName(String name) {
        List<Category> cats = em.createNamedQuery("Category.findByName", Category.class)
                .setParameter("name", name).getResultList();

        if (cats.isEmpty()) {
            return null;
        }

        return cats.get(0);
    }

    public CategoryFacade() {
        super(Category.class);
    }

}
