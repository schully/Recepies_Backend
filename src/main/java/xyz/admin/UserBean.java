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
import xyz.admin.entities.User;
import xyz.admin.sessionbeans.UserFacade;

/**
 *
 * @author Daniel GV
 */
@Named(value = "User")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    UserFacade userFacade;

    public List<User> getUsers() {
        return userFacade.findAll();
    }
}
