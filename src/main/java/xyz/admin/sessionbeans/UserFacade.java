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
import xyz.admin.entities.User;

/**
 *
 * @author Daniel GV
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "xyz.admin_Recepies_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findByUsername(String username) {
        List<User> matches = em.createNamedQuery("User.findByUsername").setParameter("username", username)
                .getResultList();
        
        if (matches.isEmpty()) {
            return null;
        } 
        
        return matches.get(0);
    }
    
}
